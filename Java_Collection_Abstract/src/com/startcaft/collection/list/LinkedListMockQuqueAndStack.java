package com.startcaft.collection.list;

import java.util.LinkedList;

/*
 * 队列特性：先进先出（FIFO)——先进队列的元素先出队列。来源于我们生活中的队列（先排队的先办完事）
 * 
 * 堆栈特性：先进后出原则，如同一个一次只能放放置一个东西的杯子
 */
public class LinkedListMockQuqueAndStack {

	public static void main(String[] args) {

		System.out.println("----------自定义队列----------");
		MyQueue<String> myQueue = new MyQueue<String>();

		myQueue.myAdd("java01");// 5
		myQueue.myAdd("java02");// 4
		myQueue.myAdd("java03");// 3
		myQueue.myAdd("java04");// 2
		myQueue.myAdd("java05");// 1

		while (!myQueue.isNull()) {
			System.out.println(myQueue.myGet());
		}

		System.out.println("----------自定义堆栈----------");
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
 * 自定义队列类-先进先出原则
 * 
 * @author wow
 *
 * @param <E>
 */
class MyQueue<E> {

	// 内部封装一个LinkedList对象
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

	// 内部封装一个LinkedList对象
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
