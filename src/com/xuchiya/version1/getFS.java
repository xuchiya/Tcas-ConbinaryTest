package com.xuchiya.version1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class getFS
{
//  public static void main(String[] args)
//  {
//    getFS test = new getFS();
//    List<Integer> list = Arrays.asList(new Integer[] { Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(10), Integer.valueOf(11), Integer.valueOf(12) });
//    test.getFailureSchemas(list, 0);
//    
//
//    Set<List<Integer>> FailSchema = test.FailSchema;
//    Set<List<Integer>> CorrectSchema = new HashSet<List<Integer>>();
//    
//
//
//
//    System.out.println(FailSchema.size());
//    System.out.println(test
//      .ExtractFailureSchemas(FailSchema, CorrectSchema).size());
//  }
	
  //递归得到测试用例的子模式   包含（-1，-1，-1，。。。。。。）
  Set<List<Integer>> FailSchema = new HashSet<List<Integer>>();
  int length = 12;
  List<Integer> temp = new ArrayList<Integer>();
 
  public void getFailureSchemas(List<Integer> list, int i)
  {
	//i表示的是测试用例的第几位参数  
    if (i == length - 1)
    {
      temp.add(-1);
      List<Integer> temp2 = new ArrayList<Integer>(temp.size());
      Collections.copy(temp2, temp);
      FailSchema.add(temp2);
      temp.remove(list.size() - 1);
      temp.add(list.get(i));
      temp2 = new ArrayList<Integer>(temp.size());
      Collections.copy(temp2, temp);
      FailSchema.add(temp2);
      temp.remove(list.size() - 1);
      return;
    }
    temp.add(-1);
    getFailureSchemas(list, i + 1);
    temp.remove(temp.size() - 1);
    temp.add(list.get(i));
    getFailureSchemas(list, i + 1);
    temp.remove(temp.size() - 1);
  }
  
  
  //从候选错误故障模式中 去除 正确的模式  
  public Set<List<Integer>> ExtractFailureSchemas(Set<List<Integer>> FailSchema, Set<List<Integer>> CorrectSchema)
  {
    Iterator<List<Integer>> itFailSchema = FailSchema.iterator();
    while (itFailSchema.hasNext())
    {
      List<Integer> temp3 = itFailSchema.next();
      Iterator<List<Integer>> itCorrectSchema = CorrectSchema.iterator();
      while (itCorrectSchema.hasNext())
      {
        List<Integer> temp4 = itCorrectSchema.next();
        //按照 位来比较 如果 temp3是temp4 的子模式，则从候选故障模式中删去temp3，因为temp4是正确模式，正确模式的子模式不是故障模式
        if (IsSubSchema(temp3, temp4))
        {
          itFailSchema.remove();
          break;
        }
      }
    }
    return FailSchema;
  }
  
  
  //判断前者是否是 后者的 子模式 是 返回 true 不是返回false
  boolean IsSubSchema(List<Integer> s1, List<Integer> s2)
  {
    if (s1.size() != s2.size()) {
      return false;
    }
    for (int i = 0; i < s1.size(); i++) {
      if ((!s1.get(i).equals(s2.get(i))) && (!s1.get(i).equals(-1))) {
        return false;
      }
    }
    return true;
  }
}
