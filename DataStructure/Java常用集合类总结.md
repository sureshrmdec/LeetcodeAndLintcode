## Java常用集合类总结

Java

* * *

### List接口与其实现类

List类似于数组，可以通过索引来访问元素，实现该接口的常用类有

ArrayList、

LinkedList、

Vector、

Stack等。

#### ArrayList

ArrayList是动态数组，可以根据插入的元素的数量自动扩容，而使用者不需要知道其内部是什么时候进行扩展的，把它当作足够容量的数组来使用即可。   
ArrayList访问元素的方法

get是常数时间，因为是直接根据下标索引来访问的，而

add方法的时间复杂度是

O(n)，因为需要移动元素，将新元素插入到合适的位置。   
ArrayList是非线程安全的，即它没有同步，不过，可以通过

Collections.synchronizedList()静态方法返回一个同步的实例，如：

1. 

List synList =Collections.synchronizedList(list);

数组扩容：ArrayList在插入元素的时候，都会检查当前的数组大小是否足够，如果不够，将会扩容到当前容量 * 1.5 + 1（加1是为了当前容量为1时，也能扩展到2），即把原来的元素全部复制到一个两倍大小的新数组，将旧的数组抛弃掉(等待垃圾回收)，这个操作是比较耗时，因此建议在创建ArrayList的时候，根据要插入的元素的数量来初步估计

Capacity，并初始化ArrayList，如：

1. 

ArrayList list =newArrayList(100);

这样，在插入小于100个元素的时候都是不需要进行扩容的，能够带来性能的提升，当然，如果对这个容量估计大了，可能会带来一些空间的损耗。

#### LinkedList

LinkedList也实现了List接口，其内部实现是使用双向链表来保存元素，因此插入与删除元素的性能都表现不错。它还提供了一些其它操作方法，如在头部、尾部插入或者删除元素，因此，可以用它来实现栈、队列、双向队列。   
由于是使用链表保存元素的，所以随机访问元素的时候速度会比较慢(需要遍历链表找到目标元素)，这一点相比ArrayList的随机访问要差，ArrayList是采用数组实现方式，直接使用下标可以访问到元素而不需要遍历。因此，在需要频繁随机访问元素的情况下，建议使用ArrayList。   
与ArrayList一样，LinkedList也是非同步的，如果需要实现多线程访问，则需要自己在外部实现同步方法。当然也可以使用

Collections.synchronizedList()静态方法。

#### Vector

Vector是ArrayList的线程同步版本，即是说Vector是同步的，支持多线程访问。除此之外，还有一点不同时，当容量不够时，Vector默认扩展一倍容量，而ArrayList是当前容量 * 1.5 + 1

#### Stack

Stack是一种后进先出的数据结构，继承自Vector类，提供了

push、

pop、

peek（获得栈顶元素）等方法。

### Set接口

Set是不能包含重合元素的容器，其实现类有HashSet，继承于它的接口有SortedSet接口等。Set中提供了加、减、和交等集合操作函数。Set不能按照索引随机访问元素，这是它与List的一个重要区别。

#### HashSet

HashSet实现了Set接口，其内部是采用HashMap实现的。放入HashSet的对象最好重写

hashCode、

equals方法，因为默认的这两个方法很可能与你的业务逻辑是不一致的，而且，要同时重写这两个函数，如果只重写其中一个，很容易发生意想不到的问题。   
记住下面几条规则：

- 相等对象，hashCode一定相等。
- 不等对象，hashCode不一定不相等。
- 两个对象的hashCode相同，不一定相等。
- 两个对象的hashCode不同，一定相等。

#### TreeSet

TreeSet同样的Set接口的实现类，同样不能存放相同的对象。它与HashSet不同的是，TreeSet的元素是按照顺序排列的，因此用TreeSet存放的对象需要实现

Comparable接口。

### Map接口

Map集合提供了按照“键值对”存储元素的方法，一个键唯一映射一个值。集合中“键值对”整体作为一个实体元素时，类似List集合，但是如果分开来年，Map是一个两列元素的集合：键是一列，值是一列。与Set集合一样，Map也没有提供随机访问的能力，只能通过键来访问对应的值。   
Map的每一个元素都是一个

Map.Entry，这个实体的结构是

&lt; Key, Value &gt;样式。

#### HashMap

HashMap实现了Map接口，但它是非线程安全的。HashMap允许

key值为

null，

value也可以为

null。

#### Hashtable

Hashtable也是Map的实现类，继承自Dictionary类。它与HashMap不同的是，它是线程安全的。而且它不允许

key为

null，

value也不能为

null。   
由于它是线程安全的，在效率上稍差于HashMap。

### List总结

ArrayList内部实现采用动态数组，当容量不够时，自动扩容至（当前容量*1.5+1）。元素的顺序按照插入的顺序排列。默认初始容量为10。   
contains复杂度为O(n)，add复杂度为分摊的常数，即添加n个元素需要O(n)时间，remove为O(n)，get复杂度为O(1)   
随机访问效率高，随机插入、删除效率低。ArrayList是非线程安全的。

LinkedList内部使用双向链表实现，随机访问效率低，随机插入、删除效率高。可以当作堆栈、队列、双向队列来使用。LinkedList也是非线程安全的。

Vector跟ArrayList是类似的，内部实现也是动态数组，随机访问效率高。Vector是线程安全的。

Stack是栈，继承于Vector，其各种操作也是基于Vector的各种操作，因此其内部实现也是动态数组，先进后出。Stack是线程安全的。

List使用场景

- 对于需要快速插入、删除元素，应该使用LinkedList
- 对于需要快速随机访问元素，应该使用ArrayList
- 如果List需要被多线程操作，应该使用Vector，如果只会被单线程操作，应该使用ArrayList

### Set总结

HashSet内部是使用HashMap实现的，HashSet的key值是不允许重复的，如果放入的对象是自定义对象，那么最好能够同时重写

hashCode与

equals函数，这样就能自定义添加的对象在什么样的情况下是一样的，即能保证在业务逻辑下能添加对象到HashSet中，保证业务逻辑的正确性。另外，HashSet里的元素不是按照顺序存储的。HashSet是非线程安全的。

TreeSet存储的元素是按顺序存储的，如果是存储的元素是自定义对象，那么需要实现Comparable接口。TreeSet也是非线程安全的。

LinkedHashSet继承自HashSet，它与HashSet不同的是，LinkedHashSet存储元素的顺序是按照元素的插入顺序存储的。LinkedHashSet也是非线程安全的。

### Map总结

HashMap存储键值对。当程序试图将一个

key-value对放入 HashMap 中时，程序首先根据该

key的

hashCode()返回值决定该

Entry的存储位置：如果两个

Entry的

key的

hashCode() 返回值相同，那它们的存储位置相同。如果这两个

Entry的

key通过

equals比较返回

true，新添加

Entry的

value将覆盖集合中原有

Entry的 

value，但

key不会覆盖。如果这两个

Entry的

key通过

equals 比较返回

false，新添加的

Entry将与集合中原有

Entry形成

Entry 链，而且新添加的 Entry 位于 Entry 链的头部。看下面HashMap添加键值对的源代码：

1. 

public V put(K key, V value){
2. 

if(key ==null)
3. 

return putForNullKey(value);
4. 

int hash = hash(key.hashCode());
5. 

int i = indexFor(hash, table.length);
6. 

for(Entry&lt;K,V&gt; e = table[i]; e !=null; e = e.next){
7. 

Object k;
8. 

if(e.hash == hash &&((k = e.key)== key || key.equals(k))){
9. 

            V oldValue = e.value;
10. 

            e.value = value;
11. 

            e.recordAccess(this);
12. 

return oldValue;
13. 

}
14. 

}
16. 

    modCount++;
17. 

    addEntry(hash, key, value, i);
18. 

returnnull;
19. 

}
21. 

void addEntry(int hash, K key, V value,int bucketIndex){
22. 

Entry&lt;K,V&gt; e = table[bucketIndex];
23. 

    table[bucketIndex]=newEntry&lt;&gt;(hash, key, value, e);
24. 

if(size++&gt;= threshold)
25. 

        resize(2* table.length);
26. 

}

HashMap允许

key、

value值为

null。HashMap是非线程安全的。

Hashtable是HashMap的线程安全版本。而且，

key、

value都不允许为

null。

哈希值的使用不同: Hashtable直接使用对象的hashCode，如下代码：

1. 

int hash = key.hashCode();
2. 

int index =(hash &0x7FFFFFFF)% tab.length;

而HashMap重新计算hash值，如下代码：

1. 

int hash = hash(key.hashCode());
2. 

int i = indexFor(hash, table.length);
4. 

staticint hash(int h){
5. 

// This function ensures that hashCodes that differ only by
6. 

// constant multiples at each bit position have a bounded
7. 

// number of collisions (approximately 8 at default load factor).
8. 

    h ^=(h &gt;&gt;&gt;20)^(h &gt;&gt;&gt;12);
9. 

return h ^(h &gt;&gt;&gt;7)^(h &gt;&gt;&gt;4);
10. 

}
11. 

staticint indexFor(int h,int length){
12. 

return h &(length-1);
13. 

}

扩展容量不同： Hashtable中hash数组默认大小是11，增加的方式是 old*2+1。HashMap中hash数组的默认大小是16，而且一定是2的指数。
