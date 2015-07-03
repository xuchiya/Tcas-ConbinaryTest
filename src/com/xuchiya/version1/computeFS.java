package com.xuchiya.version1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class computeFS {
	public static void main(String[] args) throws IOException {
		for (int number = 13; number <= 41; number++) {
			System.out.println(number + "started");
			getAllFS tt = new getAllFS();
			getFS ee = new getFS();
			computeFS qqq = new computeFS();
			Set<List<Integer>> rsCorrect = qqq.getSet("diffcorrectcase"
					+ number);
			Set<List<Integer>> rsFail = qqq.getSet("difffaultcase" + number);

			Set<List<Integer>> parent = new HashSet<List<Integer>>();

			Iterator<List<Integer>> it = rsCorrect.iterator();
			parent = tt.getAllFailureSchemas(rsFail);
			while (it.hasNext()) {
				Set<List<Integer>> result = new HashSet<List<Integer>>();
				result.add(it.next());
				parent = ee.ExtractFailureSchemas(parent, result);
			}
			FileWriter writer = new FileWriter("./sonFS" + number);
			FileWriter writer2 = new FileWriter("./parentFS" + number);
			BufferedWriter bw = new BufferedWriter(writer);
			BufferedWriter bw2 = new BufferedWriter(writer2);
			bw2.write("parent:");
			bw2.write("\n");
			Iterator<List<Integer>> parentop = parent.iterator();
			while (parentop.hasNext()) {
				bw2.write((parentop.next()).toString());
				bw2.write("\n");
			}
			bw2.close();
			writer2.close();

			Set<List<Integer>> son = tt.reduceParent(parent);
			Iterator<List<Integer>> op = son.iterator();
			bw.write("son:");
			bw.write("\n");
			while (op.hasNext()) {
				bw.write((op.next()).toString());
				bw.write("\n");
			}
			bw.close();
			writer.close();
			System.out.println(number + "completed");
		}
	}

	public Set<List<Integer>> getSet(String path) throws IOException {
		Set<List<Integer>> result = new HashSet<List<Integer>>();

		String pathroot = "./";
		String filepath = pathroot + path;
		FileReader reader = new FileReader(filepath);
		BufferedReader bufreader = new BufferedReader(reader);
		String str = null;
		while ((str = bufreader.readLine()) != null) {
			List<Integer> temp = new ArrayList<Integer>();
			String[] s = str.split(" ");
			for (String perStr : s) {
				temp.add(Integer.parseInt(perStr));
			}
			result.add(temp);
		}
		bufreader.close();
		return result;
	}
}
