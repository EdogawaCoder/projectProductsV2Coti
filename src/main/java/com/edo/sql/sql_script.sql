create table category(
	id 		uuid			primary key,
	name	varchar(50)		not null unique
	);



insert into category(id,name) values(gen_random_uuid(), 'Eletros');
insert into category(id,name) values(gen_random_uuid(), 'Info');
insert into category(id,name) values(gen_random_uuid(), 'Papers');
insert into category(id,name) values(gen_random_uuid(), 'Cloths');
insert into category(id,name) values(gen_random_uuid(), 'Bed, Table and Bath');
insert into category(id,name) values(gen_random_uuid(), 'Games');
insert into category(id,name) values(gen_random_uuid(), 'Others');


select id, name from category order by name;

====================================================================================

create table product(
	id			uuid				primary key,
	name		varchar(100)		not null,	
	price		numeric(10, 2)		not null,
	quantity	int					not null,
	created_at	timestamp			not null,
	active		boolean				not null,
	category_id	uuid				not null,
	foreign key(category_id)
		references category(id)
	);

====================================================================================


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

====================================================================================

select
	c.name as name_category,
	round(avg(p.quantity),2) as average_price
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



