/**
 * 电脑主要运行示例
 * 主板上面有很多PCI插槽，这些PCI插槽就是提供给网卡，声卡，显卡使用的。
 * @author wow
 *
 */
public class MainBoradDemo {

	public static void main(String[] args) {
		MainBorad mainBorad = new MainBorad();
		mainBorad.word(new NetCard());
		mainBorad.word(new SoundCard());
		//以后有再多的诸如此类的卡，也不怕,这些卡只需要实现PCI，就可以在我们的主板上运行。
		//mainBorad.word(new XxxCard());
	}

}

//PCI插槽接口
interface PCI{
	void run();
	void close();
}

//主板类, 主板的运行依赖于PCI插槽
class MainBorad{
	
	public void word(PCI pci){
		pci.run();
		pci.close();
	}
}
//某公司生产的网卡想要在我们的主板上运行，就必须实现PCI接口
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

//同理，某公司生产的声卡想要在我们的主板上运行，就必须实现PCI接口
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
