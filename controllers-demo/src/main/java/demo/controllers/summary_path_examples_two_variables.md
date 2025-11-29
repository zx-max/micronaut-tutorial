# Requests from command line

## PathExamplesController

### Example cases for variables in path :

| id | test case | end-of-pre-path | pre-fix    | post-fix |
|----|-----------|-----------------|------------|----------|
|    |           | /,absent        | /,+,absent | *,absent |

#### Two variables in template

| id | test case   | end-of-pre-path | pre-fix | post-fix |
|----|-------------|-----------------|---------|----------|
| 1  | /{name,x}   | /               | absent  | absent   |
| 2  | /{+name,x}  | /               | +       | absent   |
| 3  | /{/name,x}  | /               | /       | absent   |
| 4  | {name,x}    | absent          | absent  | absent   |
| 5  | {+name,x}   | absent          | +       | absent   |
| 6  | {/name,x}   | absent          | /       | absent   |
| 7  | /{name,x*}  | /               | absent  | *        |
| 8  | /{+name,x*} | /               | +       | *        |
| 9  | /{/name,x*} | /               | /       | *        |
| 10 | {name,x*}   | absent          | absent  | *        |
| 11 | {+name,x*}  | absent          | +       | *        |
| 12 | {/name,x*}  | absent          | /       | *        |

| template       | url                                                                              | result                                                   |
|----------------|----------------------------------------------------------------------------------|----------------------------------------------------------|
| /case-1/{name} | http://localhost:8080/two-variable-in-template/path-examples/case-1              | 404                                                      |
|                | http://localhost:8080/two-variable-in-template/path-examples/case-1/             | 404                                                      |
|                | http://localhost:8080/two-variable-in-template/path-examples/case-1\             | 404                                                      |
|                | http://localhost:8080/two-variable-in-template/path-examples/case-1/foo          | 404                                                      |
|                | http://localhost:8080/two-variable-in-template/path-examples/case-1/foo/bar      | 404                                                      |
|                | http://localhost:8080/two-variable-in-template/path-examples/case-1/foo,bar      | 404                                                      |
|                | http://localhost:8080/two-variable-in-template/path-examples/case-1/foo;bar      | 404                                                      |
|                | http://localhost:8080/two-variable-in-template/path-examples/case-1/foo:bar      | 404                                                      |
|                | http://localhost:8080/two-variable-in-template/path-examples/case-1a/foo         | 404                                                      |
|                | http://localhost:8080/two-variable-in-template/path-examples/case-1a/foo/bar     | 404                                                      |
|                | http://localhost:8080/two-variable-in-template/path-examples/case-1b/foo#aaa     | case-1b/{aaa}{#hash}, name: f, hash: null, x: o, y:o     |
|                | http://localhost:8080/two-variable-in-template/path-examples/case-1b/NxYyyy#aaa  | case-1b/{aaa}{#hash}, name: N, hash: null, x: x, y:Yyyyy |
|                | http://localhost:8080/two-variable-in-template/path-examples/case-1b/foo/bar#aaa | 404                                                      |
|                | http://localhost:8080/two-variable-in-template/path-examples/case-1c/foo         | 404                                                      |

```powershell
### /case-1/{name}
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-1' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-1/' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-1\' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-1/foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-1/foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-1/foo,bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-1/foo;bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-1/foo:bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-1a/foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-1a/foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-1b/foo#aaa' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-1b/NxYyyyy#aaa' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-1b/foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-1b/foo/bar#aaa' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-1c/foo' -Method Get
```

| template                | url                                                                               | result              |
|-------------------------|-----------------------------------------------------------------------------------|---------------------|
| /case-2a/{+name,x}/here | http://localhost:8080/two-variable-in-template/path-examples/case-2/              | 404                 |
|                         | http://localhost:8080/two-variable-in-template/path-examples/case-2/./foo/bar     | name: f, x: oo/bar  |
|                         | http://localhost:8080/two-variable-in-template/path-examples/case-2//foo/bar      | name: /, x: foo/bar |
|                         | http://localhost:8080/two-variable-in-template/path-examples/case-2/foo/bar       | name: f, x: oo/bar  |
|                         | http://localhost:8080/two-variable-in-template/path-examples/case-2/foo           | name: f, x: oo      |
|                         | http://localhost:8080/two-variable-in-template/path-examples/case-2a/foo/bar/here | name: f, x: oo/bar  |

```powershell
### /case-2a/{+name}/here
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-2/' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-2/./foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-2//foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-2/foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-2/foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-2a/foo/bar/here' -Method Get
```

| template        | url                                                                          | result             |
|-----------------|------------------------------------------------------------------------------|--------------------|
| /case-3/{/name} | http://localhost:8080/two-variable-in-template/path-examples/case-3//foo/    | name: foo, x: null |
|                 | http://localhost:8080/two-variable-in-template/path-examples/case-3//foo     | name: foo, x: null |
|                 | http://localhost:8080/two-variable-in-template/path-examples/case-3/foo/bar  | 404                |
|                 | http://localhost:8080/two-variable-in-template/path-examples/case-3//foo/bar | name: foo, x: bar  |
|                 | http://localhost:8080/two-variable-in-template/path-examples/case-3/         | 404                |

```powershell
### /case-3/{/name,x}
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-3//foo/' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-3//foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-3/foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-3//foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-3//' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-3/' -Method Get
```

| template        | url                                                                        | result |
|-----------------|----------------------------------------------------------------------------|--------|
| /case-4{name,x} | http://localhost:8080/two-variable-in-template/path-examples/case-4foo     | 404    |
|                 | http://localhost:8080/two-variable-in-template/path-examples/case-4foo/bar | 404    |

```powershell
### /case-4{name,x}
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-4foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-4foo/bar' -Method Get
```

| template         | url                                                                                | result                     |
|------------------|------------------------------------------------------------------------------------|----------------------------|
| /case-5{+name,x} | http://localhost:8080/two-variable-in-template/path-examples/case-5foo             | name: f, x: oo             |
|                  | http://localhost:8080/two-variable-in-template/path-examples/case-5foo/bar         | name: f, x: oo/bar         |
|                  | http://localhost:8080/two-variable-in-template/path-examples/case-5/foo/bar        | name: /, x: foo/bar        |
|                  | http://localhost:8080/two-variable-in-template/path-examples/case-5foo/bar/123/abc | name: f, x: oo/bar/123/abc |

```powershell
### /case-5{+name,x}
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-5foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-5foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-5/foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-5foo/bar/123/abc' -Method Get
```

| template                  | url                                                                                   | result                                              |
|---------------------------|---------------------------------------------------------------------------------------|-----------------------------------------------------|
| /case-6{/name}            | http://localhost:8080/two-variable-in-template/path-examples/case-6/foo               | name: foo, x: null                                  |
|                           | http://localhost:8080/two-variable-in-template/path-examples/case-6/foo/bar           | name: foo, x: bar                                   |
| /case-6a{/name,x}{/a}{/b} | http://localhost:8080/two-variable-in-template/path-examples/case-6a/foo/bar/123/bbb  | name: foo, x: bar, a:Optional[123], b:Optional[bbb] |
| /case-6b{/name,x}         | http://localhost:8080/two-variable-in-template/path-examples/case-6b/foo/bar          | name: Optional[foo], x:Optional[bar]                |
| /case-6c{/name,x}         | http://localhost:8080/two-variable-in-template/path-examples/case-6c/foo/bar          | name: foo, x:bar                                    |
| /case-6d{/name,x}/here    | http://localhost:8080/two-variable-in-template/path-examples/case-6d/foo/bar/here     | name: foo, x:bar                                    |
| /case-6e{/name,x,y}/here  | http://localhost:8080/two-variable-in-template/path-examples/case-6e/foo/bar/123/here | name: foo, x:bar, y:123                             |

```powershell
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-6/foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-6/foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-6a/foo/bar/123/bbb' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-6b/foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-6c/foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-6d/foo/bar/here' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-6e/foo/bar/123/here' -Method Get
```

| template         | url                                                                          | result            |
|------------------|------------------------------------------------------------------------------|-------------------|
| /case-7/{name*}  | http://localhost:8080/two-variable-in-template/path-examples/case-7/foo      | name: f, x:oo     |
|                  | http://localhost:8080/two-variable-in-template/path-examples/case-7/foo/bar  | 404               |
| /case-8/{+name*} | http://localhost:8080/two-variable-in-template/path-examples/case-8/foo      | name: f, x:oo     |
|                  | http://localhost:8080/two-variable-in-template/path-examples/case-8/foo/bar  | name: f, x:oo/bar |
| /case-9/{/name*} | http://localhost:8080/two-variable-in-template/path-examples/case-9/foo      | 404               |
|                  | http://localhost:8080/two-variable-in-template/path-examples/case-9//foo     | Bad Request       |
|                  | http://localhost:8080/two-variable-in-template/path-examples/case-9/foo/bar  | 404               |
|                  | http://localhost:8080/two-variable-in-template/path-examples/case-9//foo/bar | name: foo, x:bar  |

```powershell
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-7/foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-7/foo/bar' -Method Get

Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-8/foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-8/foo/bar' -Method Get

Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-9/foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-9//foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-9/foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-9//foo/bar' -Method Get
```

| template         | url                                                                          | result             |
|------------------|------------------------------------------------------------------------------|--------------------|
| /case-10{name*}  | http://localhost:8080/two-variable-in-template/path-examples/case-10foo      | name: f, x:oo      |
|                  | http://localhost:8080/two-variable-in-template/path-examples/case-10/foo     | 404                |
|                  | http://localhost:8080/two-variable-in-template/path-examples/case-10/foo/bar | 404                |
| /case-11{+name*} | http://localhost:8080/two-variable-in-template/path-examples/case-11foo      | name: f, x:oo      |
|                  | http://localhost:8080/two-variable-in-template/path-examples/case-11/foo     | name: /, x:foo     |
|                  | http://localhost:8080/two-variable-in-template/path-examples/case-11/foo/bar | name: /, x:foo/bar |

```powershell
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-10foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-10/foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-10/foo/bar' -Method Get

Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-11foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-11/foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-11/foo/bar' -Method Get
```

| template                    | url                                                                                    | result            |
|-----------------------------|----------------------------------------------------------------------------------------|-------------------|
| /case-12{/name*}            | http://localhost:8080/two-variable-in-template/path-examples/case-12/foo               | Bad Request       |
|                             | http://localhost:8080/two-variable-in-template/path-examples/case-12/foo/bar           | name: foo, x:bar  |
|                             | http://localhost:8080/two-variable-in-template/path-examples/case-12/foo/bar/aaa       | 404               |
| /case-13/xxx{.name,x}       | http://localhost:8080/two-variable-in-template/path-examples/case-13/xxx.pdf.zip       | name: pdf, x: zip |
| /case-13a/xxx{.name,x}/here | http://localhost:8080/two-variable-in-template/path-examples/case-13a/xxx.pdf.zip/here | name: pdf, x: zip |

```powershell
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-12/foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-12/foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-12/foo/bar/aaa' -Method Get

Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-13/xxx.pdf.zip' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/two-variable-in-template/path-examples/case-13a/xxx.pdf.zip/here' -Method Get
```
