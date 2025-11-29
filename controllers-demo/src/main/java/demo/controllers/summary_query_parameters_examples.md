# Requests from command line

## QueryParametersExamplesController

```powershell
Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/case-01?name=/foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/case-02?name=/foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/case-03?x=/foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/case-04?x=/foo/bar;y=yyy' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/case-05?x=/foo/bar;y=yyy;z=zzz' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/case-06?x=/foo/bar;y=yyy;z=zzz' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/case-06?x=/foo/bar&y=yyy&z=zzz' -Method Get
Invoke-WebRequest  `
-Uri 'http://localhost:8080/query-parameters/query-010?name=1965&name=2000&name=2012;count=one;count=two;count=three'  `
-Method Get

Invoke-WebRequest `
-Uri 'http://localhost:8080/query-parameters/query-009?name=1965&name=2000&name=2012;count=one;count=two;count=three' `
-Method Get

Invoke-WebRequest `
-Uri 'http://localhost:8080/query-parameters/query-008?name=1965&name=2000&name=2012&count=one&count=two&count=three' `
-Method Get

Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-007?name=1965&name=2000&name=2012' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-007?name=1965&name=2000&name=2012' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-006?name=1024&empty' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-006?name=1024&' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-006?name=1024' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-005?aa=nnn&bb=bbb' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-004?var=foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-004?var=foo&aa=3,bb=hello world' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-004?var=foo&aa=3&bb=hello world' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-003?var=foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-003?var=foo&aa=3&bb=hello world' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-002?var=foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-002?var=foo&aa=3,bb=hello world' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/query-parameters/query-002?var=foo&aa=3&bb=hello world' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/annotations/multiple-query-values-2/list?aa=aaaa&bb=true' -Method Get

Invoke-WebRequest -Uri 'http://localhost:8080/annotations/multiple-query-values-3/list?tag=v_tag&bar=v_bar,sdfa'  `
-Method Get
```
