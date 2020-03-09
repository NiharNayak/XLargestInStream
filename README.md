## xLargest Elements In Stream (Line by line from Large file).

### Idea of Solution:
Idea is to keep x-largest elements in a Queue (Min heap)
At any point of time heap will contain the minimum value at root (helps to find the min value at O(1) ). among already seen value
1. Read line parse the input 
1. If the the new element value is greater or equal to min value in the queue
2. Remove the min element (i.e root element in heap) from queue.
   Add the new element to root (then hifify will occur.)
3. Else: (No need to add the element as it's less than the min value.)
4. repeat from steps 1 to 4 until end of file.
 
 
To achieve all this in java we can create a PriorityQueue which will work as min heap.

### Complexity analysis 

#### Time complexity
x => numeric value for finding x-largest element.
n => Number of lines to be processed.
Add to Queue - O(log(x))
Check if duplicate Id (not value) exists - O(x)
Peek/Poll - O(1)

#### Space complexity
Extra space for PriorityQueue of size x. - O(x)

### How to Build
```
mvn clean install
```

### How to run
```
java -cp target/find-x-largets-stream.jar:. com.nihar.timeseries.data.analysis.run.FindXLargest
```

```
Usage: 
 java -cp target/find-x-largets-stream.jar:. com.nihar.timeseries.data.analysis.run.FindXLargest <x-value-to-find-largest-values> <input-file-fully-qualified-path (optional)>
```

##### Run by giving input file.
`java -cp target/find-x-largets-stream.jar:. com.nihar.timeseries.data.analysis.run.FindXLargest 3 data/stream_data.txt`

Output:
```
Ids having 3 largest value are bellow:
1426828086
1426828029
1426828028
```
##### Run by giving input on console.

```
java -cp target/find-x-largets-stream.jar:. com.nihar.timeseries.data.analysis.run.FindXLargest 2                       130 ↵
No input file provided , please enter input in console
1426828011 9
Do you want to continue ? [y/n]
y
1426828028 350
Do you want to continue ? [y/n]
y
1426828037 25
Do you want to continue ? [y/n]
y
1426828066 111
Do you want to continue ? [y/n]
y
1426828057 231
Do you want to continue ? [y/n]
y
1426828029 350
Do you want to continue ? [y/n]
n
Ids having 2 largest value are bellow:
1426828028
1426828029
```

#### Test case analysis for duplicate values.
In the input file there is 4 duplicate values. with 3 values with unique ID.
```
1426828028 350
1426828029 350
1426828029 350
1426828030 350
```
The output for x=3 largest element will be 
```
1426828028 350
1426828029 350
1426828030 350
```
As there is duplicate record found. `1426828029 350`, will be eliminated.
The output for x=2 largest element will be 
```
1426828029
1426828030
```