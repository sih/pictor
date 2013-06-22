create column family Purchases 
with column_type = 'Super'
and comparator = UTF8Type 
and subcomparator = UTF8Type
and default_validation_class = UTF8Type
and key_validation_class=UTF8Type
and default_validation_class = UTF8Type
and column_metadata =[
{column_name: vendor, validation_class: UTF8Type}
{column_name: product, validation_class: UTF8Type}
{column_name: price, validation_class: UTF8Type}
{column_name: longitude, validation_class: UTF8Type}
{column_name: latitude, validation_class: UTF8Type}
{column_name: secret, validation_class: BooleanType}
];