-- Create database ShopApp;
-- use ShopApp;
create table users(
    id int primary key auto_increment,
    fullname varchar(100) default '',
    phone_number varchar(10) not null,
    address varchar(200) default '',
    password varchar(100) not null,
    created_at Datetime,
    update_at datetime,
    is_active tinyint(1) default 1,
    date_of_birth date,
    facebook_account_id int default 0,
    google_account_id int default 0
);
alter table users add column role_id int;
create table roles(
    id int primary key,
    name varchar(255) not null,
);
create table tokens(
    id int primary key auto_increment,
    token varchar(255) unique not null,
    token_type varchar(255) not null,
    expiration_date datetime,
    revoked tinyint(1) not null,
    expired tinyint(1) not null,
    user_id int,
    foreign key (user_id) references users(id)
);
create table social_accounts(
    id int primary key auto_increment,
    provider varchar(20) not null comment 'Tên nhà social network',
    provider_id varchar(50) not null,
    email varchar(255) not null comment 'Email tài khoản',
    name varchar(100) not null comment 'Tên người dùng',
    user_id int, 
    foreign key (user_id) references users(id)
);
create table categories(
    id int primary key auto_increment,
    name varchar(255) comment 'Tên danh mục'
);
create table products(
    id int primary key auto_increment,
    name varchar(255) comment 'Tên sản phẩm',
    price float not null check(price >= 0),
    thumbnail varchar(255) default '',
    created_at timestamp,
    update_at timestamp,
    category_id int,
    foreign key (category_id) references categories(id)
);
create table orders(
    id int primary key auto_increment,
    user_id int,
    foreign key (user_id) references users(id),
    fullname varchar(255) default '',
    email varchar(100) default '',
    phone_number varchar(20) not null,
    address varchar(255) not null,
    note varchar(100) default '',
    order_date datetime default current_timestamp,
    status varchar(20),
    total_money float check(total_money >=0)
);
alter table orders add column 'shipping_method' varchar(100);
alter table orders add column 'shipping_address' varchar(200);
alter table orders add column 'shipping_date' Date;
alter table orders add column 'tracking_number' varchar(100);
alter table orders add column 'payment_method' varchar(100);

alter table orders add column active tinyint(1);
alter table orders
modify column status enum('pending', 'processing', 'shipped','delivered','cancelled')
comment 'Trạng thái đơn hàng';
create table order_details(
    id int primary key auto_increment,
    order_id int,
    foreign key (order_id) references orders(id),
    product_id int,
    foreign key (product_id) references products(id),
    price float check(price >=0),
    number_of_products int check(number_of_products > 0),
    total_money float check (total_money >=0)
    color varchar(20) default ''

);

