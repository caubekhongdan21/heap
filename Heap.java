package ex9;


import java.lang.reflect.Array;

public class Heap<E extends Comparable<E>> {
    private E[] data;
    private final int MAX_SIZE;
    private int currentSize;

    public Heap(Class<E> dataType, int size) {
        this.MAX_SIZE = size;
        this.data = (E[]) Array.newInstance(dataType, MAX_SIZE);
        currentSize = 0;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public E[] getData() {
        return data;
    }

    /**
     * B1: Tăng kích thước của heap lên 1 để có chỗ chứa phần tử mới.
     * B2: Thêm phần tử mới vào cuối heap.
     * B3: Sàng lên để tái cân bằng lại heap. ( sang theo maxheap)
     * Giả định bài này ta sẽ thao tác trên max heap.
     *
     * @param e
     * @return
     */
    public boolean add(E e) {
        currentSize++;
        if (currentSize < MAX_SIZE) {
            data[currentSize - 1] = e;// them phan tu vao cuoi
            siftUp(currentSize - 1);
            return true;
        }
        return false;
    }

    private void siftUp(int i) {
        var parentIndex = (i - 1) / 2;
        while (data[i].compareTo(data[parentIndex]) > 0) {
            E tmp = data[i];
            data[i] = data[parentIndex];
            data[parentIndex] = tmp;
            i = parentIndex;
            parentIndex = (i - 1) / 2;
        }
    }

    /*
    B1: xác định vị trí node cần xóa gọi là index.
B2: nếu index hợp lệ, gán phần tử cuối cho phần tử tại vị trí index.
B3: xóa bỏ phần tử cuối vừa sử dụng ở bước 2.
B4: sàng xuống để tái cân bằng lại các phần tử trong heap ở nhánh có node cha ở vị trí index.
     */
    public boolean remove(E e) {
        var index = findNode(e);
        if (index >= 0) {
            data[index] = data[currentSize - 1];
            data[currentSize - 1] = null;
            currentSize--;
            siftDown(index);
            return true;
        }
        return false;
    }

    /*
    B1: tìm vị trí node cần sửa gọi là index.
    B2: nếu tìm thấy, gán giá trị mới cho node tại vị trí index.
    B3: tìm vị trí node cha của node vừa cập nhật.
    B4: nếu node cha < node tại vị trí index, sàng lên.
    B5: ngược lại, sàng xuống để tái cân bằng lại heap.
     */
    public boolean update(E oldNode, E newNode) {
        var index = findNode(oldNode);
        if (index >= 0) {
            data[index] = newNode;
            var parentIndex = (index - 1) / 2;
            if (data[parentIndex].compareTo(data[index]) < 0) {
                siftUp(index);
            } else {
                siftDown(index);
            }
            return true;
        }
        return false;
    }

    /**
     * left < currentSize and right < currentSize là vì để xử lý node lá
     *
     * @param index
     */
    private void siftDown(int index) {
        var largest = index;
        while (true) {
            var left = 2 * largest + 1;
            var right = 2 * largest + 2;
            if (left < currentSize && data[left].compareTo(data[largest]) > 0) {
                largest = left;
            }
            if (right < currentSize && data[right].compareTo(data[largest]) > 0) {
                largest = right;
            }
            if (largest != index) {
                E tmp = data[index];
                data[index] = data[largest];
                data[largest] = tmp;
                index = largest;
            } else {
                break;
            }
        }
    }

//    public void siftDown(int index) {
//        var largest = index;
//        var left = 2 * index + 1;
//        var right = 2 * index + 2;
//        if (left < currentSize && data[left].compareTo(data[largest]) > 0) {
//            largest = left;
//        }
//        if (right < currentSize && data[right].compareTo(data[largest]) > 0) {
//            largest = right;
//        }
//        if (largest != index) {
//            E tmp = data[index];
//            data[index] = data[largest];
//            data[largest] = tmp;
//            siftDown(largest);
//        }
//    }

    private int findNode(E e) {
        for (int i = 0; i < currentSize; i++) {
            if (data[i].compareTo(e) == 0) {
                return i;
            }
        }
        return -1;
    }

    public void showElements() {
        for (int i = 0; i < currentSize; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }
}
