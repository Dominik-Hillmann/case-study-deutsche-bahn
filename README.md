# case-study-deutsche-bahn
A case study for an application to Deutsche Bahn.

## Installation 
`cd` into the root of this repository.

```sh
docker build -t operation-sites . 
docker run -d -t -p 8080:8080 --name operation-sites operation-sites
```

## Requests