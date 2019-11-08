import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
class Node {   
	public char c; 
	public int f; 
	public Node left; 
	public Node right; 
	
	Node(){
		this.c='#';
		this.f=0;
		this.left=null;
		this.right=null;
	}
}


class nodeComparator implements Comparator<Node> { //to compare btw 2 nodes by freq
    public int compare(Node x,Node y) 
    { 
        return x.f - y.f; 
    } 
}

public class Huffman {

	
	static void generateCode(Node n,String s,Vector<String> k,Vector<String> code) {
		if (n!=null&&n.c!='#') {
			k.addElement(n.c+"");
			code.addElement(s);
		}
		if(n!=null) {
			generateCode(n.right,s+"1",k,code);
			generateCode(n.left,s+"0",k,code);
		}
	}

	static void saveTable(String dir,String fileName,Vector<String> k,Vector<String> code) {
		String newfile=(fileName.replaceAll(".txt", "")+"comp_table.txt");
		FileWriter fileWriter = null;
		try {
			//System.out.println(dir+"\\"+newfile);
			fileWriter = new FileWriter(dir+"\\"+newfile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        BufferedWriter bufferedWriter =new BufferedWriter(fileWriter);
        for(int i=0;i<k.size();i++) {
        	try {
				bufferedWriter.write(k.elementAt(i)+ "|"+code.elementAt(i)+"|");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        try {
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void loadTable(String dir,String fileName,Vector<String> k,Vector<String> code) throws IOException {
		String newfile=(fileName.replaceAll(".txt", "")+"_table.txt");
		String line=null;
		String all="";
		try {
			FileReader fileReader = new FileReader(dir+"\\"+newfile);
			BufferedReader bufferedReader =new BufferedReader(fileReader);
			while((line = bufferedReader.readLine()) != null) {
				all+=line;
			}
			String[] data=all.split("\\|");
			for(int i=1;i<data.length;i+=2) {
				k.addElement(data[i-1]);
				code.addElement(data[i]);
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static String comp(String s,String dir,String fileName) {
		Set<String> a=new HashSet<String>();/**to make sure that the char will be calc. only once */
		Vector<Node> b=new Vector<Node>();/**all the real nodes*/
		//get all nodes
		for(int i=0;i<s.length();i++) {
			int c=0;
			for(int j=0;j<s.length();j++) {
				if(s.charAt(i)==s.charAt(j)&&!a.contains(s.charAt(i)+"")) {
					c++;
				}
			}
			//System.out.println(c);
			if(!a.contains(s.charAt(i)+"")) {
				a.add(s.charAt(i)+"");
				Node temp =new Node();
				temp.c=s.charAt(i);
				temp.f=c;
				b.add(temp);
			}
		}
		
		PriorityQueue<Node> q= new PriorityQueue<Node>( new nodeComparator());
		
		for(int i=0;i<b.size();i++) {//copying nodes from vector b to queue
			q.add(b.elementAt(i));
		}
		
		Node root=new Node();
		while(q.size()>1) {
			Node x=q.peek();
			q.poll();
			Node y=q.peek();
			q.poll();
			Node temp=new Node();
			temp.f=x.f+y.f;
			temp.left=y;
			temp.right=x;
			q.add(temp);
		}
		root=q.peek();
		Vector<String> k=new Vector<String>();
		Vector<String> code=new Vector<String>();
		generateCode(root,"",k,code);
		String ans="";
		for(int i=0;i<s.length();i++) {
			int x=k.indexOf(s.charAt(i)+"");
			ans+=code.elementAt(x);
		}
		saveTable(dir,fileName,k,code);
		k.clear();
		code.clear();
		return ans;
	}
	
	static String decomp(String s,String dir,String fileName) throws IOException {
		Vector<String> k=new Vector<String>();
		Vector<String> code=new Vector<String>();
		loadTable(dir,fileName,k,code);
		String temp="",v="";
		for(int i=0;i<s.length();i++) {
			int x=code.indexOf(temp+s.charAt(i));
			if(x!=-1) {
				v+=k.elementAt(x);
				temp="";
			}
			else {
				temp+=s.charAt(i);
			}
		}
		k.clear();
		code.clear();
		return v;
	}
	
	public static void main(String[] args) {
		Scanner data=new Scanner(System.in);
		String s=data.nextLine();
		//System.out.println(s);
		
		String p=s.replaceAll(".txt", "");
		System.out.println(p);
		/*String ans=comp(s,k,code);
		String temp="",v="";
		for(int i=0;i<ans.length();i++) {
			int x=code.indexOf(temp+ans.charAt(i));
			if(x!=-1) {
				v+=k.elementAt(x);
				temp="";
			}
			else {
				temp+=ans.charAt(i);
			}
		}
		System.out.println(v);*/
		data.close();
	}

}
