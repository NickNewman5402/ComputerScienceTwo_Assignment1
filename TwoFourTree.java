package CS2Project1;

public class TwoFourTree {
	
	
	
    private class TwoFourTreeItem {
        int values = 1;
        int value1 = 0;                             // always exists.
        int value2 = 0;                             // exists iff the node is a 3-node or 4-node.
        int value3 = 0;                             // exists iff the node is a 4-node.
        boolean isLeaf = true;
        
        TwoFourTreeItem parent = null;              // parent exists iff the node is not root.
        TwoFourTreeItem leftChild = null;           // left and right child exist iff the note is a non-leaf.
        TwoFourTreeItem rightChild = null;          
        TwoFourTreeItem centerChild = null;         // center child exists iff the node is a non-leaf 3-node.
        TwoFourTreeItem centerLeftChild = null;     // center-left and center-right children exist iff the node is a non-leaf 4-node.
        TwoFourTreeItem centerRightChild = null;

        public boolean isTwoNode() {
        	if(this.values == 1) {
        		return true;
        	} else {
        		return false;
        	}
        }

        public boolean isThreeNode() {
        	if(this.values == 2) {
        		return true;
        	} else {
        		return false;
        	}
        }

        public boolean isFourNode() {
        	if(this.values == 3) {
        		return true;
        	} else {
        		return false;
        	}
        }

        public boolean isRoot() {
        	// this is wrong and needs to be fixed
        	if(this.values == 1) {
        		return true;
        	} else {
        		return false;
        	}
        }

        public TwoFourTreeItem(int value1) {
            this.value1 = value1;
            this.values = 1;         // Indicate that this is a 2-node (one value)
            this.isLeaf = true;      // Initially, this node is a leaf
        }

        public TwoFourTreeItem(int value1, int value2) {
        	this.value1 = value1;
        	this.value2 = value2;
        	this.values = 2;
        	this.isLeaf = true;   	
        }

        public TwoFourTreeItem(int value1, int value2, int value3) {
        	this.value1 = value1;
        	this.value2 = value2;
        	this.value3 = value3;
        	this.values = 3;
        	this.isLeaf = true;
        }

        private void printIndents(int indent) {
            for(int i = 0; i < indent; i++) System.out.printf("  ");
        }

        public void printInOrder(int indent) {
            if(!isLeaf) leftChild.printInOrder(indent + 1);
            printIndents(indent);
            System.out.printf("%d\n", value1);
            if(isThreeNode()) {
                if(!isLeaf) centerChild.printInOrder(indent + 1);
                printIndents(indent);
                System.out.printf("%d\n", value2);
            } else if(isFourNode()) {
                if(!isLeaf) centerLeftChild.printInOrder(indent + 1);
                printIndents(indent);
                System.out.printf("%d\n", value2);
                if(!isLeaf) centerRightChild.printInOrder(indent + 1);
                printIndents(indent);
                System.out.printf("%d\n", value3);
            }
            if(!isLeaf) rightChild.printInOrder(indent + 1);
        }
    }// End of Private Class
    
    /*************************************************************************************************************/

    
    TwoFourTreeItem root = null;

    public boolean addValue(int value) {
    	
    	
    	if (root == null) {
    	    root = new TwoFourTreeItem(value);  // Initialize root node with the value
    	    return true;
    	}
    	
    	TwoFourTreeItem current = root;
    	
    	
    	//Traversing through the tree
    	while(!current.isLeaf) {
	        if (current.isTwoNode()) {
	        	        	
	            if (value < current.value1) {
	            	current.leftChild.parent = current;
	                current = current.leftChild;
	            } else {
	            	current.rightChild.parent = current;
	                current = current.rightChild;
	            }
	            
	            
	        } else if (current.isThreeNode()) {
	        
	        	if (value < current.value1) {
	            	current.leftChild.parent = current;
	        		current = current.leftChild;
	        	} else if (value < current.value2) {
	            	current.centerChild.parent = current;
	        		current = current.centerChild;
	        	} else {
	            	current.rightChild.parent = current;
	        		current = current.rightChild;
	        	}
	        	
	        	
	        } else if (current.isFourNode()) {
	        	
	        	current.parent = current;
	        	
	        	if (value < current.value1) {
	            	current.leftChild.parent = current;
	        		current = current.leftChild;
	        	} else if ( value < current.value2) {
	            	current.centerLeftChild.parent = current;
	        		current = current.centerLeftChild;	
	        		} else if (value < current.value3) {
		            	current.centerRightChild.parent = current;
	        			current = current.centerRightChild;
	        		} else {
		            	current.rightChild.parent = current;
	        			current = current.rightChild;
	        	}
	        }    		
    	}
    	
    	//Now if we are a leaf node
    	/****Two Node leaf****/
        if (current.isTwoNode()) {
            if (value < current.value1) {
                current.value2 = current.value1; // Shift value1 to value2
                current.value1 = value; // Insert new value at value1
                current.values += 1;
            } else {
                current.value2 = value; // Insert new value at value2
                current.values += 1;
            } 
        /****Three Node leaf****/
            } else if (current.isThreeNode()) {
            	if (value < current.value1) {
            		current.value3 = current.value2;
            		current.value2 = current.value1;
            		current.value1 = value;
            		current.values += 1;
            	} else if (value < current.value2) {
            		current.value3 = current.value2;
            		current.value2 = value;
            		current.values += 1;
            	} else {
            		current.value3 = value;
            		current.values += 1;
            	}
        /****Four Node leaf****/
            } else if (current.isFourNode()) {
            	
            	if(current != root && current.parent.isTwoNode()) {            	
            	
            	current.parent.value2 = current.value2;
            	current.parent.values = 2;
            	current.parent.centerChild = new TwoFourTreeItem(current.value1);
            	current.value1= current.value3;
            	current.value2 = value;
            	current.value3 = 0;
            	current.isLeaf = true;
            	current.values = 2;
            	
            	} else if(current != root && current.parent.isThreeNode()) {
            		
            	current.parent.value3 = current.value2;
            	current.parent.values = 3;
            	current.parent.centerLeftChild = current.parent.centerChild;
            	current.parent.centerRightChild = new TwoFourTreeItem(current.value1);
            	current.value1= current.value3;
            	current.value2 = value;
            	current.value3 = 0;
            	current.isLeaf = true;
            	current.values = 2;
            	
            	} else if (current != root && current.parent.isFourNode()) {
            		// This is where I need to come back and finish. I am good up to 
            		// adding value 29. 
            		
            		// split the parent
            		
            		/* I believe the code below here for the next 21 lines is an else condition of
            		 an if, else if statement where if is if current.parent is a two node insert
            		 value to current.parent.value2 else if current.parent is a three node insert
            		 value to current.parent.value3 else... do what i have coded. This is being 
            		 uploaded to github under commit */
            		
            		TwoFourTreeItem newRoot = new TwoFourTreeItem(current.parent.value2); // root is 7
            		newRoot.isLeaf = false;
            		newRoot.values = 1;
            		
            		newRoot.leftChild = new TwoFourTreeItem(current.parent.value1); // left child 3
            		newRoot.leftChild.isLeaf = false;
            		newRoot.leftChild.values = 1;
            		newRoot.leftChild.leftChild = current.parent.leftChild; // left child left child 2
            		newRoot.leftChild.leftChild.values = 1;
            		newRoot.leftChild.rightChild = current.parent.centerLeftChild; // left child right child 5 
            		newRoot.leftChild.rightChild.values = 1;
            		
            		newRoot.rightChild = new TwoFourTreeItem(current.parent.value3); // right child is 13
            		newRoot.rightChild.isLeaf = false;
            		newRoot.rightChild.values = 1;
            		newRoot.rightChild.leftChild = current.parent.centerRightChild; // right child left child is 11
            		newRoot.rightChild.values = 1;
            		newRoot.rightChild.rightChild = current; // right child right child 17 19 23
            		newRoot.rightChild.rightChild.values = 1;
            		// split right child
            		newRoot.rightChild.value2 = current.value2; // 19 moves up next to 13
            		newRoot.rightChild.values = 2;
            		newRoot.rightChild.centerChild = new TwoFourTreeItem(current.value1);	
            		newRoot.rightChild.rightChild.value1 = current.value3;
            		newRoot.rightChild.rightChild.value2 = value; 
            		newRoot.rightChild.rightChild.value3 = 0;
            		newRoot.rightChild.rightChild.values = 2;
            		
            		root = newRoot;
            		
            		// When i get to 47 things mess up. 47 basically becomes the root of the tree. Because I am not 
            		// handeling the parent of the parent. 
            		
            		
            		return true;
            	            	
            	} else {
            	
            	// initial run of the code when all i have is root. 
            	current.leftChild = new TwoFourTreeItem(current.value1);
            	current.rightChild = new TwoFourTreeItem(current.value3, value);
            	current.value1= current.value2;
            	current.value2 = 0;
            	current.value3 = 0;
            	current.isLeaf = false;
            	current.values = 1;
            	
            	}
            	

            }
        
        return false;
    }
    
    
    public boolean hasValue(int value) {
        return false;
    }

    public boolean deleteValue(int value) {
    	// This is going to end up traversing the tree finding a value and deleting it
    	// this is not going to be used for clearing keys in a node
        return false;
    }

    public void printInOrder() {
        if(root != null) root.printInOrder(0);
    }

    public TwoFourTree() {

    }
}