# Fictitious Blog
## DB Migration
Before start, you should migrate db throught flyway
```aidl
mvn flyway:migrate
```

## POST BLog
sample payload POST
### Create
POST localhost:8080/post
```aidl
{
    "title":"Test title 1",
    "content":"test content 1",
    "tag": [
        {
            "tag": {
                "id": 1,
                "label":"News"
            }
        }
    ]
    
}
```

### Fetch
GET localhost:8080/post/1

### Fetch All
GET localhost:8080/post

### Modify
PUT localhost:8080/post/1
```aidl
{
    "title":"Test title 1x",
    "content":"test content 1x",
    "tag": [
        
    ]
    
}
```

### DELETE
DELETE localhost:8080/post/1


## TAG Blog
sample payload POST
### Create
POST localhost:8080/tag
```aidl
{
    "label":"News"
}
```

### Fetch
GET localhost:8080/tag/1

### Fetch All
GET localhost:8080/tag

### Modify
PUT localhost:8080/tag/1
```aidl
{
    "label":"News modif"
}
```

### DELETE
DELETE localhost:8080/tag/1
