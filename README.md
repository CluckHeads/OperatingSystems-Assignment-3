# Operating Systems Assignment 3

1.	Develop a Java monitor, called Garden. Three people (threads) work in the garden and their names are Jordan, Charles, and Tracy. 


2.	Their coordination is described as follows:
   *	Jordan digs 10 holes.
   *	Charles plants something in each of the 10 holes.
   *  Tracy fills each of the 10 holes after being planted.
   *	Jordan will wait if there are five unfilled holes.
   *	Charles will wait if all holes have plants in them.
   *	Tracy will wait if all holes are either empty or already filled.
   *	It takes Jordan less than 100 milliseconds to dig a hole.
   *	It takes Charles less than 500 milliseconds to plant something in a hole.
   *	It takes Tracy less than 500 milliseconds to fill a hole.
