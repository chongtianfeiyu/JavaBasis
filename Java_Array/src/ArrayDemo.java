/*******************************************
 * Java中的数据
 * @author startcaft
 *	
 * 数组的定义格式：
 * 1，元素类型 [] 数组名称 = new 元素类型[元素个数];
 * 		int [] arr = new int[5];//推荐写法
 * 		int arras[] = new int[5];
 * 
 * 2,静态初始化方式，不能写长度
 * 		int[] arrs = new int[]{1,2,3,4,5};
 * 		int[] arrs = {1,2,3,4,5};//jdk5.0后才能这样书写
 * 
 * 3，boolean类型的数组默认值为false
 */
public class ArrayDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int [] arr={1,2,4,5,63,8,7,9,5,4}; 
		
		printArray(arr);
		
		int maxNumber = getMaxNumber(arr);
		System.out.println("arr数组中最大的数值为是------>>" + maxNumber);
		
		System.out.println("数组选择排序前:");
		int [] sortArray={1,2,5,6,4,7,3};  
		printArray(sortArray);
		System.out.println("数组选择排序前:");
		selectSort(sortArray);
		
		int [] bubbleArray={3,9,51,65,43,73,13};  
		System.out.println("数组冒泡排序前:");
		printArray(bubbleArray);
		System.out.println("数组冒泡排序后:");
		bubbleSort(bubbleArray);
		
		
	}
	
	/**
	 * 输出一个数组
	 * 
	 * @param array		int类型数组
	 * 
	 * @author startcaft
	 */
	private static void printArray(int [] array){
		for(int x=0;x<array.length;x++){
			
			if(x != array.length -1){
				System.out.println(array[x] + ",");
			}
			else{
				System.out.println(array[x]);
			}
		}
	}
	
	/**
	 * 获取数组中的最大的数字
	 * 
	 * @param array		int类型数组
	 * @author wow
	 */
	private static int getMaxNumber(int [] array){
		int max = array[0];
		for(int i=0;i<array.length;i++){
			if(array[i] > max){
				max = array[i];
			}
		}
		return max;
	}
	
	/**
	 * 对数组进行选择排序并输出
	 * 
	 * @author wow
	 * 
	 * @param array		int类型数组
	 */
	private static void selectSort(int [] array){
		for(int i=0;i<array.length;i++){
			for(int j=i;j<array.length;j++){
				if(array[i] < array[j]){
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
		
		printArray(array);
	}
	
	/**
	 * 冒泡排序	-x:让每一次比较的元素减少,-1 :不让下标越界  
	 * 
	 * @author wow
	 * 
	 * @param arr
	 */
	private static void bubbleSort(int [] arr){
		for(int x=0;x<arr.length-1;x++){  
            //-x:让每一次比较的元素减少,-1 :不让下标越界  
            for (int y = 0; y < arr.length-1-x; y++) {    
                if(arr[y]>arr[y+1]){  
                    int temp = arr[y];  
                    arr[y] = arr[y+1];  
                    arr[y+1] = temp;  
                }  
            }  
        }  
		
		printArray(arr);
	}
}
