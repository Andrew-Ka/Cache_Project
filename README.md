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
  
  
| Address Accessed        | Hit/Miss           | Set 0  | Set 0  | Set 1           | Set 1  |
| :-------------: |:-------------:| :-----:| :-------------: |:-------------:| :-----:|
| 0      | `Miss` | 0 | - | - | - |
| 8      | `Miss` | 0 | 8 | - | - |
| 0      | `Hit`  | 0 | 8 | - | - |
| 6      | `Miss` | 0 | 8 | 6 | - |
| 8      | `Hit`  | 0 | 8 | 6 | - |  



