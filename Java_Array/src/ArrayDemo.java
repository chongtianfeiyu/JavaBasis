/*******************************************
 * Java�е�����
 * @author startcaft
 *	
 * ����Ķ����ʽ��
 * 1��Ԫ������ [] �������� = new Ԫ������[Ԫ�ظ���];
 * 		int [] arr = new int[5];//�Ƽ�д��
 * 		int arras[] = new int[5];
 * 
 * 2,��̬��ʼ����ʽ������д����
 * 		int[] arrs = new int[]{1,2,3,4,5};
 * 		int[] arrs = {1,2,3,4,5};//jdk5.0�����������д
 * 
 * 3��boolean���͵�����Ĭ��ֵΪfalse
 */
public class ArrayDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int [] arr={1,2,4,5,63,8,7,9,5,4}; 
		
		printArray(arr);
		
		int maxNumber = getMaxNumber(arr);
		System.out.println("arr������������ֵΪ��------>>" + maxNumber);
		
		System.out.println("����ѡ������ǰ:");
		int [] sortArray={1,2,5,6,4,7,3};  
		printArray(sortArray);
		System.out.println("����ѡ������ǰ:");
		selectSort(sortArray);
		
		int [] bubbleArray={3,9,51,65,43,73,13};  
		System.out.println("����ð������ǰ:");
		printArray(bubbleArray);
		System.out.println("����ð�������:");
		bubbleSort(bubbleArray);
		
		
	}
	
	/**
	 * ���һ������
	 * 
	 * @param array		int��������
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
	 * ��ȡ�����е���������
	 * 
	 * @param array		int��������
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
	 * ���������ѡ���������
	 * 
	 * @author wow
	 * 
	 * @param array		int��������
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
	 * ð������	-x:��ÿһ�αȽϵ�Ԫ�ؼ���,-1 :�����±�Խ��  
	 * 
	 * @author wow
	 * 
	 * @param arr
	 */
	private static void bubbleSort(int [] arr){
		for(int x=0;x<arr.length-1;x++){  
            //-x:��ÿһ�αȽϵ�Ԫ�ؼ���,-1 :�����±�Խ��  
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
