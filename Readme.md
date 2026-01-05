## Query 1: Basic JOIN - Show All Content with Categories
### Write a query that displays: content_id, title, and category_name for all shows.
```
select
    content_id,
    title,
    category_name
from content
join category using(category_id);
```
![Query 1 output](./sql_output_images/Screenshot%202025-11-30%20115635.png)



## Query 2: Top Performers - Sorted by Popularity
### Write a query that shows all content (title, rating, views) sorted by views in descending order (most viewed first).
```
select
    title,
    rating,
    views_in_millions as views
from content
order by views desc;
```
![Query 2 output](./sql_output_images/Screenshot%202025-11-30%20115703.png)


## Query 3: Category Analytics - Average Rating per Category
### Write a query that calculates the average rating for each category, showing category_name and average_rating.
```
select
    category_name,
    avg(rating) as average_rating
from category
join content using(category_id)
group by category_id;
```
![Query 3 output](./sql_output_images/Screenshot%202025-11-30%20115732.png)


## Query 4: High-Rated Content with Filters
### Find all content with a rating above 8.5 AND views greater than 100 million. Display title, rating, views, and category_name.
```
select
    title,
    rating,
    views_in_millions as views,
    category_name
from content
join category using(category_id)
where rating > 8.5 and views_in_millions > 100;
```
![Query 1 output](./sql_output_images/Screenshot%202025-11-30%20115756.png)


## Query 5: Index Demonstration
```
explain analyze select
    content_id,
    title,
    category_name
from content
join category using(category_id);
```

## output
```
-> Nested loop inner join  (cost=3.45 rows=8) (actual time=0.113..0.161 rows=8 loops=1)
    -> Table scan on category  (cost=0.65 rows=4) (actual time=0.0436..0.0475 rows=4 loops=1)
    -> Index lookup on content using category_id (category_id=category.category_id)  (cost=0.55 rows=2) (actual time=0.024..0.0265 rows=2 loops=4)
```

## creating index on category_id on content table
```
create index idx_category_id on content(category_id);
```

```
explain analyze select
    content_id,
    title,
    category_name
from content
join category using(category_id);
```

## output
```
-> Nested loop inner join  (cost=3.45 rows=8) (actual time=0.0742..0.126 rows=8 loops=1)
    -> Table scan on category  (cost=0.65 rows=4) (actual time=0.0376..0.042 rows=4 loops=1)
    -> Index lookup on content using category_id (category_id=category.category_id)  (cost=0.55 rows=2) (actual time=0.016..0.0192 rows=2 loops=4)
```

### Write 2-3 sentences explaining: Why did the index improve (or not improve) performance?
*Index on category_id did increase performance. Before we created the index, for every row from the category table we used to do a full table scan on content table to locate the matching record as per the join condition. But after creating the index, we just look for the category_id in the index structure (logarithmic time) and we get references to all the correspoding records in the content table, thereby avoiding a full table scan and saving time.*



## Why 1: Why do we use Foreign Keys?

*Foreign keys are used to enforce data consistency and integrity. By having foreign key constraint we avoid having orphaned data and keep the relationship b/w tables valid. We make sure that a row isn't inserted into the referencing table unless the foreign key value already exists in the referenced table*



## Why 2: Why is ACID important for this database?
### Imagine 1000 users trying to watch "Stranger Adventures" at the same time, and the system needs to update the view count. What could go wrong without ACID properties?

*ACID properties help transactions maintain data consistency and integrity. As for the example, if we didn't have ACID, then just without ISOLATION, views would get incremented incorrectly due to improper concurrency control leading to data incosistency. And Atomicity helps to make sure that every transaction either gets completed or rejected as a whole, like if commiting the view count fails, the whole transaction should be rolled back. And consistency takes database from one valid state to another valid state. Here views should always be positive and not become negative. Durability makes sures that commited data remains permanently in database, even after a system breakdown. So together ACID plays a crucial role in maintaing data consistency and integrity.*



## Why 3: Why would we create an index on category_id?
### When StreamFlix homepage loads, it runs hundreds of queries filtering by category. How does an index help?

*In the homepage we would display movies according to the category they belong Without having the index on category_id, we would have to scan the entire content table, row by row looking for the mathing entries which is generally inefficient. So by having an index on category_id, we would just search the index structure and there we will get pointers (references) to all the correspoding rows of the original content table. This would make our app faster and efficient.*

## Request A (Category Audit): "Get a list of all content titles that belong to the 'Documentaries' 
## category, was released in 2024 and has a rating higher than 8.0. We need the Title and the 
## Category Name in the final report." 

```
select
	title,
	category_name
from category
join content using(category_id)
where category_name = "Documentaries" and release_year = 2024 and rating > 8;
```

## Request B (Success Score Analysis): "We don't just want to know Views or Ratings 
## separately. We need a combined 'Success Score' for our dashboard." 

```
select
	title,
	rating + views_in_millions as success_score
from content
where rating + views_in_millions > 100;
```