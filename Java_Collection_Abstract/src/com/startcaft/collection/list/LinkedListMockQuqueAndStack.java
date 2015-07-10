package com.startcaft.collection.list;

import java.util.LinkedList;

/*
 * �������ԣ��Ƚ��ȳ���FIFO)�����Ƚ����е�Ԫ���ȳ����С���Դ�����������еĶ��У����Ŷӵ��Ȱ����£�
 * 
 * ��ջ���ԣ��Ƚ����ԭ����ͬһ��һ��ֻ�ܷŷ���һ�������ı���
 */
public class LinkedListMockQuqueAndStack {

	public static void main(String[] args) {

		System.out.println("----------�Զ������----------");
		MyQueue<String> myQueue = new MyQueue<String>();

		myQueue.myAdd("java01");// 5
		myQueue.myAdd("java02");// 4
		myQueue.myAdd("java03");// 3
		myQueue.myAdd("java04");// 2
		myQueue.myAdd("java05");// 1

		while (!myQueue.isNull()) {
			System.out.println(myQueue.myGet());
		}

		System.out.println("----------�Զ����ջ----------");
		MyStack<String> MyStack = new MyStack<String>();

		MyStack.myAdd("java01");// 5
		MyStack.myAdd("java02");// 4
		MyStack.myAdd("java03");// 3
		MyStack.myAdd("java04");// 2
		MyStack.myAdd("java05");// 1

		while (!MyStack.isNull()) {
			System.out.println(MyStack.myGet());
		}
	}
}

/**
 * �Զ��������-�Ƚ��ȳ�ԭ��
 * 
 * @author wow
 *
 * @param <E>
 */
class MyQueue<E> {

	// �ڲ���װһ��LinkedList����
	private LinkedList<E> linkedList;

	MyQueue() {
		linkedList = new LinkedList<E>();
	}

	public void myAdd(E e) {
		linkedList.offerFirst(e);
	}

	public E myGet() {
		return linkedList.pollLast();
	}

	public boolean isNull() {
		return linkedList.isEmpty();
	}
}

class MyStack<E> {

	// �ڲ���װһ��LinkedList����
	private LinkedList<E> linkedList;

	MyStack() {
		linkedList = new LinkedList<E>();
	}

	public void myAdd(E e) {
		linkedList.offerFirst(e);
	}

	public E myGet() {
		return linkedList.pollFirst();
	}

	public boolean isNull() {
		return linkedList.isEmpty();
	}
}
