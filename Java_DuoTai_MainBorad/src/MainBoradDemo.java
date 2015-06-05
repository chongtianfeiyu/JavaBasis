/**
 * ������Ҫ����ʾ��
 * ���������кܶ�PCI��ۣ���ЩPCI��۾����ṩ���������������Կ�ʹ�õġ�
 * @author wow
 *
 */
public class MainBoradDemo {

	public static void main(String[] args) {
		MainBorad mainBorad = new MainBorad();
		mainBorad.word(new NetCard());
		mainBorad.word(new SoundCard());
		//�Ժ����ٶ���������Ŀ���Ҳ����,��Щ��ֻ��Ҫʵ��PCI���Ϳ��������ǵ����������С�
		//mainBorad.word(new XxxCard());
	}

}

//PCI��۽ӿ�
interface PCI{
	void run();
	void close();
}

//������, ���������������PCI���
class MainBorad{
	
	public void word(PCI pci){
		pci.run();
		pci.close();
	}
}
//ĳ��˾������������Ҫ�����ǵ����������У��ͱ���ʵ��PCI�ӿ�
class NetCard implements PCI{

	@Override
	public void run() {
		System.out.println("A Compant NetCard Running...");
	}

	@Override
	public void close() {
		System.out.println("A Compant NetCard Closed...");
	}
	
}

//ͬ��ĳ��˾������������Ҫ�����ǵ����������У��ͱ���ʵ��PCI�ӿ�
class SoundCard implements PCI{

	@Override
	public void run() {
		System.out.println("A Compant SoundCard Running...");
	}

	@Override
	public void close() {
		System.out.println("A Compant SoundCard Closed...");
	}
}
