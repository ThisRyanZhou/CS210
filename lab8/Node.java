public class Node implements NodeFunctions
{
	private final int key;
	private Node parent;
	private Node left;
	private Node right;
	private int count;
	
	public Node(int key)
	{
		this.key = key;
		parent = null;
		left = null;
		right = null;
		count = 0;
	}
		
	public int getKey()
	{
		return key;
	}
	
	public Node getParent()
	{
		return parent;
	}
	
	public Node getLeft()
	{
		return left;
	}
	
	public Node getRight()
	{
		return right;
	}
	
	public void setLeft(Node n)
	{
		left = n;
	}
	
	public void setRight(Node n)
	{
		right = n;
	}
	
	public void setParent(Node n)
	{
		parent = n;
	}
	
	public String toString()
	{
		String p = "";
        String l = "";
        String r = "";
        if (parent != null) {
            p = String.valueOf(parent.key);
        }
        if (left != null) {
            l = String.valueOf(left.key);
        }
        if (right != null) {
            r = String.valueOf(right.key);
        }
        return "(" + key + "," + p + "," + l + "," + r + "," + count + ")";
    }
	
	public boolean equals(Object o)
	{
		if (this == o){
			return true;
		}
        if (!(o instanceof Node)){
			return false;
		}
        Node other = (Node) o;
        return this.key == other.key;
	}
	
	public int getCount()
	{
		return count;
	}
	
	public void setCount(int count)
	{
		this.count = count;
	}
	
	public int hashCode()
	{
		return Integer.hashCode(key);
	}
}
