// mongeez formatted javascript
// %%Ignore-License

// changeset ims:Task-1
db.products.createIndex({
    "sku" : 1
}, {
    "unique" : true,
    "collation" : {
        "locale" : "en",
        "strength" : 2
    }
});

db.products.insert({
    "name": "computer",
    "description": "laptop devics",
    "price": "144",
    "categoryId": "f2b823a9-568a-4973-8c3f-af15f5228bf3",
    "image": "sadasdasdasd",
    "quantity": 10,
    "sku": "sadasd3wewqewqe"
});
//db.categories.updateMany({"identifiers": {$exists: false}}, { $set : {"identifiers":[]}});

