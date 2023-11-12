# Cache_Project
## About This Project
This project is for my Computer Architecture Course.  
This project allows a visualization of the different types of cache architecture.  


## Getting Started
It's simple, just clone the project and download!

## Comments
The FIFO is not as tested as the LRU as that is not the main focus (FIFO is less used).

## Test Case to try
4 blocks, 2-Way Set Associative, Using Addresses:  
0, 8, 0, 6, 8    

Expected Results:  

|  Address Accessed   |  Hit/Miss   |  Set 0   |  Set 0   |  Set 1   |  Set 1   |  
|:---:|:---:||:---:||:---:||:---:||:---:|  
| 0 | `Miss` | 0 | - | - | - |   
| 8 | `Miss` | 0 | 8 | - | - |   
| 0 | `Hit`  | 0 | 8 | - | - |   
| 6 | `Miss` | 0 | 8 | 6 | - |   
| 8 | `Hit`  | 0 | 8 | 6 | - |   
  
| Address Accessed        | Hit/Miss           | Set 0  | Set 0  | Set 1           | Set 1  |
| ------------- |:-------------:| -----:| ------------- |:-------------:| -----:|
| 0      | right-aligned | $1600 | col 3 is      | right-aligned | $1600 |
| 8      | centered      |   $12 | col 2 is      | centered      |   $12 |
| 0      | are neat      |    $1 | zebra stripes | are neat      |    $1 |
| 6      | right-aligned | $1600 | col 3 is      | right-aligned | $1600 |
| 8      | centered      |   $12 | col 2 is      | centered      |   $12 |



Colons can be used to align columns.

| Tables        | Are less           | Cool  |
| ------------- |:-------------:| -----:|
| col 3 is      | right-aligned | $1600 |
| col 2 is      | centered      |   $12 |
| zebra stripes | are neat      |    $1 |

There must be at least 3 dashes separating each header cell.
The outer pipes (|) are optional, and you don't need to make the 
raw Markdown line up prettily. You can also use inline Markdown.

Markdown | Less | Pretty
--- | --- | ---
*Still* | `renders` | **nicely**
1 | 2 | 3
