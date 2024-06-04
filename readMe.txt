Once you have opened the QuizGen_Group2 folder run the makefile (with "make" command) in terminal.
>make

This will compile and run. It will take about a minute for everything to compile the first time.
It will run automatically after compiling. See User Manual in report.

typing "make Test" will run the unit tests

make clean empties the bin directory.
make cleanPools removes every type of question pool stored in each folder

source code can be found in src
classes in bin
MCQs inside the MCQuestions folder
t/f questions inside the TrueFalse directory
Junit files insdie the UnitTest folder

Choose from Text File user manual
Trees
<int> -> Data Structure: BST/AVL/RedBlackTree/Binary Heap
<int> -> Format: MCQ/True-False/Fill In Numeric
<int> -> Resultant scheme: Min heap or Max heap.	Only applies to Binary Heap
<int> -> Type: Insertion/Delete/Root/etc. type questions
<int> -> Pool Size
<int> -> Data Stored: Type of input data to be randomly created (ints, floats, Strings)
Hash tables
<int> -> Data Structure: BST/AVL/RedBlackTree/Binary Heap
<int> -> Format: MCQ/True-False/Fill In Numeric
<int> -> Type: Type of question (Insertion/Collision).	This applies to MCQ format only.
<int> -> Resolution Scheme: linear probing/quadratic probing/chaining
<int> -> Pool Size
Graphs
<int> -> Data Structure: BST/AVL/RedBlackTree/Binary Heap
<int> -> Format: MCQ/True-False/Fill In Numeric
<int> -> Graph Type: BFS/Dijkstra/Bellman ford/Acyclic
<int> -> Path Cost
<int> -> 0 (optional to put 0 or nothing) 
