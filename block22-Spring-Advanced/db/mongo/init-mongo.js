db.createUser({
    user: 'root',
    pwd: 'toor',
    roles: [
        {
            role: 'readWrite',
            db: 'testDB',
        },
    ],
});
db.createCollection('app_users', { capped: false });

db.app_users.insert([
    {
        "username": "ragnar777",
        "dni": "VIKI771012HMCRG093",
        "enabled": true,
        "password_not_encrypted": "s3cr3t",
        "password": "$2a$10$PjSx4fzCV4N6Ak4CDXJvoOemiSxaeOulSzFLrao8JdissgbUUd9/y",
        "role":
            {
                "granted_authorities": ["read"]
            }
    },
    {
        "username": "heisenberg",
        "dni": "BBMB771012HMCRR022",
        "enabled": true,
        "password_not_encrypted": "p4sw0rd",
        "password": "$2a$10$zK57sLPqoWTIFHNXlrLBZO9mpuswFpJtCRVViA42jBACr7oXJhoM.",
        "role":
            {
                "granted_authorities": ["read"]
            }
    },
    {
        "username": "misterX",
        "dni": "GOTW771012HMRGR087",
        "enabled": true,
        "password_not_encrypted": "misterX123",
        "password": "$2a$10$yEusgMYzajJLmN70UA1cJOIAcW8d/9dbOqrry5DWxxP0yHenlKU1K",
        "role":
            {
                "granted_authorities": ["read", "write"]
            }
    },
    {
        "username": "neverMore",
        "dni": "WALA771012HCRGR054",
        "enabled": true,
        "password_not_encrypted": "4dmIn",
        "password": "$2a$10$BMauiIGhLOn8Ib5Feo4SFOm63iak.20.lCIf1L5P.ZkoeAowcZL3W",
        "role":
            {
                "granted_authorities": ["write"]
            }
    }
]);