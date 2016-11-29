package cn.com.core.member.utils;

import java.util.Date;

/**
 * GenerateDataHelper
 *
 * @author Le Yuan
 * @date 2016/10/21
 */
public class GenerateDataHelper {
    /**
     * 随机产生n位字母
     * @param n
     * @return
     */
    public static  String produceName(int n){
        char  []name={'A','b','c','D','E','F','G','H','i','j','K','M','L','n','o','p','q','r','s','t','u','V','w','X','y','Z'};
        StringBuilder myName=new StringBuilder();
        for (int j=0;j<n;j++){
            int m=(int)(Math.random()*25);
            myName.append(name[m]);
        }
        return  myName.toString();
    }

    /**
     * 随机产生n为数字
     * @param n
     * @return
     */
    public static  String  produceNumbers(int n){
        char  []name={'0','1','2','3','4','5','6','7','8','9'};
        StringBuilder myName=new StringBuilder();
        for (int j=0;j<n;j++){
            int m=(int)(Math.random()*9);
            myName.append(name[m]);
        }
        return  myName.toString();
    }

    /**
     * 随机产生性别
     * @return
     */
    public static String produceSex(){
         char [] sex={'男','女'};
        StringBuilder mySex=new StringBuilder();
         int m=(int)(1+Math.random()*2);
         mySex.append(sex[m-1]);
        return mySex.toString();
    }

    /**
     * 随机产生一个日期
     */
    public static Date prduceDate(){
        return new Date((int)(113+Math.random()*19),(int)(Math.random()*12),(int)(1+Math.random()*30));
    }


    /* for (int i=51;i<100;i++){
        memberMapper.insert(new Member(i+"", GenerateDataHelper.produceName(5),
                GenerateDataHelper.produceNumbers(8),GenerateDataHelper.produceSex(),GenerateDataHelper.prduceDate(),
                GenerateDataHelper.produceNumbers(11),GenerateDataHelper.produceNumbers(1),GenerateDataHelper.produceNumbers(1),
                Integer.parseInt(GenerateDataHelper.produceNumbers(3)),Integer.parseInt(GenerateDataHelper.produceNumbers(2)),GenerateDataHelper.prduceDate(),
                Integer.parseInt(GenerateDataHelper.produceNumbers(3)),Float.parseFloat(GenerateDataHelper.produceNumbers(3)),Float.parseFloat(GenerateDataHelper.produceNumbers(3)),
                GenerateDataHelper.prduceDate(),GenerateDataHelper.prduceDate()));
    }
    */

  /*  public  static  void  main(String []args){


        }*/

}
