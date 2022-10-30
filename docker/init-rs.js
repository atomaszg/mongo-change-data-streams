!/bin/sh
mongosh -u $MONGO_INITDB_ROOT_USERNAME -p '$MONGO_INITDB_ROOT_PASSWORD' --authenticationDatabase admin <<EOF
rs.initiate({
    _id: "cds-replica-set",
    members: [
        { _id: 0, host : "mongo-service-1:27017", priority: 2 },
        { _id: 1, host : "mongo-service-2:27017", priority: 1 },
    ]
})

while (! db.isMaster().ismaster ) { sleep(500) }

rs.status();

db = db.getSiblingDB("$MONGO_INITDB_DATABASE");
db.offers.insertOne({});
db.offers.deleteMany({});

print(db)

EOF