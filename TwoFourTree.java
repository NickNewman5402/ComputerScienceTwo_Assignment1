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
	                current = current.leftChild;
	            } else {
	                current = current.rightChild;
	            }
	        } else if (current.isThreeNode()) {
	        	if (value < current.value1) {
	        		current = current.leftChild;
	        	} else if (value < current.value2) {
	        		current = current.centerChild;
	        	} else {
	        		current = current.rightChild;
	        	}
	        } else if (current.isFourNode()) {
	        	if (value < current.value1) {
	        		current = current.leftChild;
	        	} else if ( value < current.value2) {
	        		current = current.centerLeftChild;	
	        		} else if (value < current.value3) {
	        			current = current.centerRightChild;
	        		} else {
	        			current = current.rightChild;
	        	}
	        }    		
    	}
    	
    	//Now if we are a leaf node
    	/****Two Node****/
        if (current.isTwoNode()) {
            if (value < current.value1) {
                current.value2 = current.value1; // Shift value1 to value2
                current.value1 = value; // Insert new value at value1
            } else {
                current.value2 = value; // Insert new value at value2
            } 
        /****Three Node****/
            } else if (current.isThreeNode()) {
            	if (value < current.value1) {
            		current.value3 = current.value2;
            		current.value2 = current.value1;
            		current.value1 = value;
            	} else if (value < current.value2) {
            		current.value3 = current.value2;
            		current.value2 = value;
            	} else {
            		current.value3 = value;
            	}
        /****Four Node****/
            } else if (current.isFourNode()) {
            	
            	return false;
            	//This needs to be completed
            }
        


    	
        return false;
    }
    
    

    public boolean hasValue(int value) {
        return false;
    }

    public boolean deleteValue(int value) {
        return false;
    }

    public void printInOrder() {
        if(root != null) root.printInOrder(0);
    }

    public TwoFourTree() {

    }
}
