package com.edo.repositories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.edo.entities.Category;
import com.edo.factories.ConnectionFactory;

public class CategoryRepository {

	public List<Category> findAll() throws Exception {

		var connection = ConnectionFactory.getConnection();

		var sql = """
				select id, name from category
				order by name
				""";

		var statement = connection.prepareStatement(sql);
		var resultSet = statement.executeQuery();

		var list = new ArrayList<Category>();


		while (resultSet.next()) {
			var category = new Category();

			category.setId(UUID.fromString(resultSet.getString("id")));
			category.setName(resultSet.getString("name"));
			list.add(category);
		}

		connection.close();

		return list;
	}
	
	public Category findById(UUID id) throws Exception {
        var connection = ConnectionFactory.getConnection();

        var sql = """
            SELECT id, name
            FROM category
            WHERE id = ?
        """;

        var statement = connection.prepareStatement(sql);
        statement.setObject(1, id);

        ResultSet rs = statement.executeQuery();

        Category category = null;
        if (rs.next()) {
            category = new Category();
            category.setId((UUID) rs.getObject("id"));
            category.setName(rs.getString("name"));
        }

        connection.close();
        return category;

}
}
