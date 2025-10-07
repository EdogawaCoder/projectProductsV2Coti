package com.edo.repositories;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.edo.dtos.responses.AvgPriceCategoryResDto;
import com.edo.dtos.responses.QntSumCategoryResDto;
import com.edo.entities.Category;
import com.edo.entities.Product;
import com.edo.factories.ConnectionFactory;

public class ProductRepository {

	public void insert(Product product) throws Exception {
		try (var connection = ConnectionFactory.getConnection(); var statement = connection.prepareStatement("""
				    insert into product (id, name, price, quantity, created_at, active, category_id)
				    values (?, ?, ?, ?, ?, ?, ?)
				""")) {

			statement.setObject(1, product.getId());
			statement.setString(2, product.getName());
			statement.setDouble(3, product.getPrice().doubleValue());
			statement.setInt(4, product.getQuantity());
			statement.setTimestamp(5, Timestamp.valueOf(product.getCreatedAt()));
			statement.setBoolean(6, product.getActive());
			statement.setObject(7, product.getCategory().getId());
			statement.execute();
		}
	}

	public Boolean update(Product product) throws Exception {

		var connection = ConnectionFactory.getConnection();

		var sql = """
				updart product
				where id = ?
				set name = ?,
				    price = ?,
				    quantity = ?,
				    category_id = ?
				where
					id = ?
				and
					active = true
				""";

		var statement = connection.prepareStatement(sql);

		statement.setString(1, product.getName());
		statement.setDouble(2, product.getPrice().doubleValue());
		statement.setInt(3, product.getQuantity());
		statement.setObject(4, product.getId());
		statement.setObject(5, product.getCategory().getId());
		var rowsAffected = statement.executeUpdate();

		connection.close();

		return rowsAffected > 0;
	}

	public boolean delete(UUID id) throws Exception {
		var connection = ConnectionFactory.getConnection();

		var sql = """
				    update product
				    set
				  active = false
				    where
				  id = ?
				    and
				  active = true
				""";

		var statement = connection.prepareStatement(sql);
		statement.setObject(1, id);
		var rowsAffected = statement.executeUpdate();

		connection.close();

		return rowsAffected > 0;
	}

	public List<Product> findAll() throws Exception {

		var connection = ConnectionFactory.getConnection();

		var sql = """
				select
					p.id,
					p.name,
					p.price,
					p.quantity,
					p.created_at,
					p.active,
					 c. id as idcategory,
					 c.name as namecategory
					from product p
					inner join category c
					on c.id = p.category_id
					where p.active = true
					order by p.name asc
				""";

		var statement = connection.prepareStatement(sql);

		var result = statement.executeQuery();

		var list = new ArrayList<Product>();

		while (result.next()) {

			var product = new Product();
			product.setCategory(new Category());

			product.setId(UUID.fromString(result.getString("id")));
			product.setName(result.getString("name"));
			product.setPrice(BigDecimal.valueOf(result.getDouble("price")));
			product.setQuantity(result.getInt("quantity"));
			product.setCreatedAt(result.getTimestamp("created_at").toLocalDateTime());
			product.setActive(result.getBoolean("active"));

			product.getCategory().setId(UUID.fromString(result.getString("idcategory")));
			product.getCategory().setName(result.getString("namecategory"));

			list.add(product);
		}

		connection.close();
		return list;
	}

	// Total quantity per products on categories.
	public List<QntSumCategoryResDto> groupByQuantityCategory() throws Exception {
		var list = new ArrayList<QntSumCategoryResDto>();

		try (var connection = ConnectionFactory.getConnection(); var statement = connection.prepareStatement("""
				    select
				        c.name as name_category,
				        sum(p.quantity) as quantity_total
				    from
				        product p
				    inner join
				        category c
				    on c.id = p.category_id
				    group by
				        name_category
				    order by
				        quantity_total desc;
				"""); var result = statement.executeQuery()) {

			while (result.next()) {
				var response = new QntSumCategoryResDto();
				response.setNameCategory(result.getString("name_category"));
				response.setTotalQuantity(result.getInt("quantity_total"));
				list.add(response);
			}
		}

		return list;
	}

	// Average quantity (or price) per category.
	public List<AvgPriceCategoryResDto> groupByMediaCategory() throws Exception {
		var list = new ArrayList<AvgPriceCategoryResDto>();

		try (var connection = ConnectionFactory.getConnection(); var statement = connection.prepareStatement("""
				    select
				        c.name as name_category,
				        round(avg(p.quantity), 2) as average_price
				    from
				        product p
				    inner join
				        category c
				    on
				        c.id = p.category_id
				    group by
				        name_category
				    order by
				        average_price desc;
				"""); var result = statement.executeQuery()) {

			while (result.next()) {
				var response = new AvgPriceCategoryResDto();
				response.setNameCategory(result.getString("name_category"));
				response.setAveragePrice(result.getDouble("average_price"));
				list.add(response);
			}
		}

		return list;
	}
}
