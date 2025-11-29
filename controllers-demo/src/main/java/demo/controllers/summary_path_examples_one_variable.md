# Requests from command line

## PathExamplesController

### Example cases for variables in path :

| id | test case | end-of-pre-path | pre-fix    | post-fix |
|----|-----------|-----------------|------------|----------|
|    |           | /,absent        | /,+,absent | *,absent |

#### One variable in template

| id | test case | end-of-pre-path | pre-fix | post-fix |
|----|-----------|-----------------|---------|----------|
| 1  | /{name}   | /               | absent  | absent   |
| 2  | /{+name}  | /               | +       | absent   |
| 3  | /{/name}  | /               | /       | absent   |
| 4  | {name}    | absent          | absent  | absent   |
| 5  | {+name}   | absent          | +       | absent   |
| 6  | {/name}   | absent          | /       | absent   |
| 7  | /{name*}  | /               | absent  | *        |
| 8  | /{+name*} | /               | +       | *        |
| 9  | /{/name*} | /               | /       | *        |
| 10 | {name*}   | absent          | absent  | *        |
| 11 | {+name*}  | absent          | +       | *        |
| 12 | {/name*}  | absent          | /       | *        |

```powershell
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/regex/blue' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/regex/orange' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/regex/aaa' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/regex/' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/regex' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/dateTime/2024-03-05' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/dateTime-2/settembre 25 2024 at 17:35:33 PM' -Method Get
```

| template               | url                                                                          | result                |
|------------------------|------------------------------------------------------------------------------|-----------------------|
| /case-1/{name}         | http://localhost:8080/one-variable-in-template/path-examples/case-1          | 404                   |
|                        | http://localhost:8080/one-variable-in-template/path-examples/case-1/         | 404                   |
|                        | http://localhost:8080/one-variable-in-template/path-examples/case-1\         | 404                   |
|                        | http://localhost:8080/one-variable-in-template/path-examples/case-1/foo      | name: foo             |
|                        | http://localhost:8080/one-variable-in-template/path-examples/case-1/foo/bar  | 404                   |
|                        | http://localhost:8080/one-variable-in-template/path-examples/case-1/foo,bar  | name: foo,bar         |
|                        | http://localhost:8080/one-variable-in-template/path-examples/case-1/foo;bar  | 404                   |
|                        | http://localhost:8080/one-variable-in-template/path-examples/case-1/foo:bar  | name: foo:bar         |
| /case-1a/{aaa}         | http://localhost:8080/one-variable-in-template/path-examples/case-1a/foo     |                       |
| /case-1b/{name}{#hash} | http://localhost:8080/one-variable-in-template/path-examples/case-1b/foo#aaa | name: foo, hash: null |

```powershell
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-1' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-1/' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-1\' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-1/foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-1/foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-1/foo,bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-1/foo;bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-1/foo:bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-1a/foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-1b/foo#aaa' -Method Get
```

| template              | url                                                                               | result         |
|-----------------------|-----------------------------------------------------------------------------------|----------------|
| /case-2a/{+name}/here | http://localhost:8080/one-variable-in-template/path-examples/case-2/              | 404            |
|                       | http://localhost:8080/one-variable-in-template/path-examples/case-2/./foo/bar     | name: foo/bar  |
|                       | http://localhost:8080/one-variable-in-template/path-examples/case-2//foo/bar      | name: /foo/bar |
|                       | http://localhost:8080/one-variable-in-template/path-examples/case-2/foo/bar       | name: foo/bar  |
|                       | http://localhost:8080/one-variable-in-template/path-examples/case-2/foo           | name: foo      |
|                       | http://localhost:8080/one-variable-in-template/path-examples/case-2a/foo/bar/here | name: foo/bar  |

```powershell
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-2/' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-2/./foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-2//foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-2/foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-2/foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-2a/foo/bar/here' -Method Get
```

| template        | url                                                                          | result    |
|-----------------|------------------------------------------------------------------------------|-----------|
| /case-3/{/name} | http://localhost:8080/one-variable-in-template/path-examples/case-3//foo/    | name: foo |
|                 | http://localhost:8080/one-variable-in-template/path-examples/case-3//foo     | name: foo |
|                 | http://localhost:8080/one-variable-in-template/path-examples/case-3//foo/bar | 404       |
|                 | http://localhost:8080/one-variable-in-template/path-examples/case-3//        | 404       |
|                 | http://localhost:8080/one-variable-in-template/path-examples/case-3/         | 404       |

```powershell
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-3//foo/' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-3//foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-3//foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-3//' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-3/' -Method Get
```

| template       | url                                                                                | result                |
|----------------|------------------------------------------------------------------------------------|-----------------------|
| /case-4{name}  | http://localhost:8080/one-variable-in-template/path-examples/case-4foo             | name: foo             |
|                | http://localhost:8080/one-variable-in-template/path-examples/case-4foo/bar         | 404                   |
| /case-5{+name} | http://localhost:8080/one-variable-in-template/path-examples/case-5foo             | name: foo             |
|                | http://localhost:8080/one-variable-in-template/path-examples/case-5foo/bar         | name: foo/bar         |
|                | http://localhost:8080/one-variable-in-template/path-examples/case-5/foo/bar        | name: /foo/bar        |
|                | http://localhost:8080/one-variable-in-template/path-examples/case-5foo/bar/123/abc | name: foo/bar/123/abc |

```powershell
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-4foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-4foo/bar' -Method Get

Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-5foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-5foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-5/foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-5foo/bar/123/abc' -Method Get
```

| template                | url                                                                               | result                                      |
|-------------------------|-----------------------------------------------------------------------------------|---------------------------------------------|
| /case-6{/name}          | http://localhost:8080/one-variable-in-template/path-examples/case-6/foo           | name: foo                                   |
|                         | http://localhost:8080/one-variable-in-template/path-examples/case-6/foo/bar       | 404                                         |
| /case-6a{/name}{/x}{/y} | http://localhost:8080/one-variable-in-template/path-examples/case-6a/foo/bar/123  | name: foo, x:Optional[bar], y:Optional[123] |
| /case-6d{/name}/here    | http://localhost:8080/one-variable-in-template/path-examples/case-6d/foo/here     | name: foo                                   |
| /case-6e{/name*}/here   | http://localhost:8080/one-variable-in-template/path-examples/case-6e/foo/here     | name: foo                                   |
|                         | http://localhost:8080/one-variable-in-template/path-examples/case-6e/foo/bar/here | 404                                         |
| /case-6e{/name*}/here   | http://localhost:8080/one-variable-in-template/path-examples/case-6f/foo/here     | name: /foo                                  |
|                         | http://localhost:8080/one-variable-in-template/path-examples/case-6f/foo/bar/here | name: /foo/bar                              |

```powershell
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-6/foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-6/foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-6a/foo/bar/123' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-6d/foo/here' -Method Get

Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-6e/foo/here' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-6e/foo/bar/here' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-6f/foo/here' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-6f/foo/bar/here' -Method Get
```

| template         | url                                                                          | result        |
|------------------|------------------------------------------------------------------------------|---------------|
| /case-7/{name*}  | http://localhost:8080/one-variable-in-template/path-examples/case-7/foo      | name: foo     |
|                  | http://localhost:8080/one-variable-in-template/path-examples/case-7/foo/bar  | 404           |
| /case-8/{+name*} | http://localhost:8080/one-variable-in-template/path-examples/case-8/foo      | name: foo     |
|                  | http://localhost:8080/one-variable-in-template/path-examples/case-8/foo/bar  | name: foo/bar |
| /case-9/{/name*} | http://localhost:8080/one-variable-in-template/path-examples/case-9/foo      | 404           |
|                  | http://localhost:8080/one-variable-in-template/path-examples/case-9//foo     | name: foo     |
|                  | http://localhost:8080/one-variable-in-template/path-examples/case-9//foo/bar | 404           |

```powershell
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-7/foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-7/foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-8/foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-8/foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-9/foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-9//foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-9//foo/bar' -Method Get
```

| template         | url                                                                          | result         |
|------------------|------------------------------------------------------------------------------|----------------|
| /case-10{name*}  | http://localhost:8080/one-variable-in-template/path-examples/case-10foo      | name: foo      |
|                  | http://localhost:8080/one-variable-in-template/path-examples/case-10/foo     | 404            |
|                  | http://localhost:8080/one-variable-in-template/path-examples/case-10/foo/bar | 404            |
| /case-11{+name*} | http://localhost:8080/one-variable-in-template/path-examples/case-11foo      | name: foo      |
|                  | http://localhost:8080/one-variable-in-template/path-examples/case-11/foo     | name: /foo     |
|                  | http://localhost:8080/one-variable-in-template/path-examples/case-11/foo/bar | name: /foo/bar |

```powershell
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-10foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-10/foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-10/foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-11foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-11/foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-11/foo/bar' -Method Get
```

| template                  | url                                                                                    | result        |
|---------------------------|----------------------------------------------------------------------------------------|---------------|
| /case-12{/name*}          | http://localhost:8080/one-variable-in-template/path-examples/case-12/foo               | name: foo     |
| /case-12{/name*}          | http://localhost:8080/one-variable-in-template/path-examples/case-12/foo.pdf           | name: foo.pdf |
|                           | http://localhost:8080/one-variable-in-template/path-examples/case-12/foo/bar           | 404           |
| /case-13/xxx{.name}       | http://localhost:8080/one-variable-in-template/path-examples/case-13/xxx.pdf.zip       | name: pdf.zip |
| /case-13a/xxx{.name}/here | http://localhost:8080/one-variable-in-template/path-examples/case-13a/xxx.pdf.zip/here | name: pdf.zip |

```powershell
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-12/foo' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-12/foo.pdf' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-12/foo/bar' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-13/xxx.pdf.zip' -Method Get
Invoke-WebRequest -Uri 'http://localhost:8080/one-variable-in-template/path-examples/case-13a/xxx.pdf.zip/here' -Method Get
```
