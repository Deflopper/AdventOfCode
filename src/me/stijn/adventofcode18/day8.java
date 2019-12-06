package me.stijn.adventofcode18;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class day8 {
	
	public static int sum;
	
	public static void main(String[] args) throws IOException {
		String input = Utils.getString(8);
		ArrayList<String> inputArr = new ArrayList();
		ArrayList<Integer> intArr = new ArrayList();
		Collections.addAll(inputArr, input.split(" "));
		for (String s : inputArr) {
			intArr.add(Integer.valueOf(s));
		}
		new Node(intArr);
		
		System.out.println("Sum of all metadata entries: " + sum);
	}
	
	public static class Node {
		int nodes,meta;
		ArrayList<Node> childNodes = new ArrayList();
		
		public Node(ArrayList<Integer> node) {
			//System.out.println("Node: " + node.toString());
			nodes = node.get(0);
			meta = node.get(1);
			if (nodes == 0) 
				return;
			
			if (meta > 0) {
				for (int i : node.subList(node.size() - meta, node.size())){
					sum+=i;
				}
			}
			node.subList(node.size() - meta, node.size()).clear();
			node.subList(0, 2).clear();

			for (;;) {
				Node n = new Node(node);
				if (n.nodes == 0 && n.meta > 0) {
					for (int i : node.subList(2, 2 + n.meta)){
						sum+=i;
					}
					node.subList(0, 2 + n.meta).clear();
				}
				if (node.isEmpty())
					break;
			}
		}
	}
	
}
