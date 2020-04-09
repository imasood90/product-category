db.auth('admin-user', 'admin-password')

db = db.getSiblingDB('category-service')

db.createUser({
    user: 'user',
    pwd: 'password',
    roles: [
        {
            role: 'root',
            db: 'admin',
        },
    ],
});

//db.createCollection('categories')


db = db.getSiblingDB('product-service')

db.createUser({
    user: 'user',
    pwd: 'password',
    roles: [
        {
            role: 'root',
            db: 'admin',
        },
    ],
});

//db.createCollection('products')

