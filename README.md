# case-study-deutsche-bahn
A case study for an application to Deutsche Bahn AG.

## Installation 
`cd` into the root of this repository.

```sh
docker build -t operation-sites . 
docker run -d -t -p 8080:8080 --name operation-sites operation-sites
```
Now the service should be running and you can make the following requests to it.

## Requests
Currently, there is only one API.
You can get more information about an operation site by querying its abbreviation.
In this case, the service runs locally and we want query the code `AAMP`.
```sh
curl --request GET --url http://localhost:8080/api/betriebsstelle/aamp
```
It will return:
```json
{
  "Name": "Hamburg Anckelmannsplatz",
  "Kurzname": "Anckelmannsplatz",
  "Typ": "Üst"
}
```
In case no operation site uses the a code, for example this query:
```sh
curl --request GET --url http://localhost:8080/api/betriebsstelle/foofoo
```
Will return a JSON with the field `Nachricht` indicating what went wrong:
```json
{
  "Nachricht": "Data does not contain operation site with RL100-Code foofoo."
}
```
If you do not provide an operation site code, the web API will suggest possible codes you can use:
```shell
curl --request GET --url http://localhost:8080/api/betriebsstelle
```

The returned JSON will look like this:
```json
{
  "Moegliche Betriebsstellencodes": [
    "SGRB",
    "SGRG",
    "SGRL",
    "SGRO",
    "SGS",
    "SGSZ",
    "SGU",
    "SGW",
    "SHAM",
    "SHAU",
    "SHB",
    "SHBG",
    // ...
  ]
}
```