## What is a variable?
- Variable is a container which holds a value.
- DataType variablename  = value;
- java is a static typed language --> you have to define datatype of variable
- java is a strong typed language --> each datatype has a range


## Variable naming Comvention
- Variable naming is case sensitive
- Variable name can be any legal identifier means can contain Unicode letters and Digits.
- Variable name can start with $, _, and letter.
- Variable name cannot be a JAVA reserved Keyword like "new", "class", "while", "for", "interface", "int", "float", etc.
- Variable should be all small if it contains only 1 word; else camel case should be followed.


## Types Of Variables

### Primitive Type
- **char**
  - 2 bytes i.e. 16 bits
  - Character representation of ASCII values
  - Range: 0 to 65535 i.e. '\u0000' to '\uffff'
  - Default value is '\u0000' i.e. NULL

- **byte**
  - 1 byte i.e. 8 bits
  - Signed 2's complement
  - Range: -128 to 127
  - signed means negative numbers are also allowed, if the msb is 0 it is positive if msb is 1 it is negative 
  say 3 -> 0011, for -3 we need to take 2s complement therefore -> 1101, if u add them ans is 0 (only 4 bits are considered)
  - default value is 0
  - default value only assigned to class member variables, not inside a method(there u have to specify the value)

- **short**
  - 2 bytes i.e. 16 bits
  - Signed 2's complement
  - Range: -32768 to 32767
  - Default value is 0

- **int**
  - 4 bytes i.e. 32 bits
  - Range: -2^31 to 2^31 - 1
  - Default value is 0
  - Signed 2's complement

- **long**
  - 8 bytes i.e. 64 bits
  - Signed 2's complement
  - Range: -2^63 to 2^63 - 1
  - Default value is 0
  - e.g., long var = 100L; // This L signifies that it is long type.

- **boolean**
  - 1 bit
  - Value: True or False
  - Default value is False

### Non-Primitive / Reference Type
- class
- interface
- array
- string
- enum




