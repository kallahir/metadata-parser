# Metadata Parser
*under development*

When building a multi-platform project it is imperative to ensure that all systems speak on the same business domain [REF01](#REF01). this project provides a metadata parser capable of providing POJO implementation for the following languages:

1. Java;
2. Kotlin;
3. Swift;

## Metadata Sample
```XML
<?xml version="1.0" encoding="UTF-8"?>

<entity name="User" type="class" visibility="public" persistable="true">
  <member name="id" type="Double" visibility="private" persistable="true" />
  <member name="name" type="String" visibility="private" persistable="true" />
  <member name="surname" type="String" visibility="private" persistable="true" />
  <member name="nickname" type="String" visibility="private" persistable="true" />
  <member name="email" type="String" visibility="private" persistable="true" />
  <member name="cpf" type="String" visibility="private" persistable="true" />
  <member name="phone1" type="String" visibility="private" persistable="true" />
  <member name="phone2" type="String" visibility="private" persistable="true" />
  <member name="age" type="Date" visibility="private" persistable="true" />
  <member name="address" type="String" visibility="private" persistable="true" />
</entity>
```

## Running the parser
On terminal type ```python parser.py <metadata_file> <output_dir>```

*P.S: output dir is optional. If not provided files will be generated on script root dir.*

## Sample output
**Java**
```Java
public class User {
	private Double id;
	private String name;
	private String surname;
	private String nickname;
	private String email;
	private String cpf;
	private String phone1;
	private String phone2;
	private Date age;
	private String address;
}
```
**Kotlin**
```Kotlin
class User {
	private var id : Double 
	private var name : String 
	private var surname : String 
	private var nickname : String 
	private var email : String 
	private var cpf : String 
	private var phone1 : String 
	private var phone2 : String 
	private var age : Date 
	private var address : String 
}
```

**Swift**
```Swift
public class User {
	private var id : Double
	private var name : String
	private var surname : String
	private var nickname : String
	private var email : String
	private var cpf : String
	private var phone1 : String
	private var phone2 : String
	private var age : Date
	private var address : String
}
```

## References
<a name="REF01">
 - REF01: Hunt, A. & Thomas, D. (1999). The Power of Plain Text. In The Pragmatic Programmer: From Journeyman to Master. Boston, Mass. Addson Wesley.
