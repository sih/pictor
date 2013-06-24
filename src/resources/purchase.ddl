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

-- row key<composite>: 	handle, yyyy-mm-dd 
-- column<composite>:	vendor, product, purchase_id
create column family CompositePurchases
with comparator = 'CompositeType(UTF8Type,UTF8Type,UTF8Type)'
and key_validation_class = 'UTF8Type'
and default_validation_class = 'UTF8Type'
;

-- column<composite>:	yyyy-mm-dd, measure (e.g. grand_total)
create column family CompositeTotals
with comparator = 'CompositeType(UTF8Type,UTF8Type)'
and key_validation_class = 'UTF8Type'
and default_validation_class = 'CounterColumnType'
;