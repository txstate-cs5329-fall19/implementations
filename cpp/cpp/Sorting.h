#pragma once
class Sorting
{
public:
	Sorting();
	virtual ~Sorting();

	template <typename T, int N>
	void bubbleSort(T(&array)[N]) {
		bool swapPerformed;
		do {
			swapPerformed = false;
			// N - 1 because inside loop, we do i + 1
			for (int i = 0; i < N - 1; i++) {
				if (array[i] > array[i + 1]) {
					int tmp = array[i + 1];
					array[i + 1] = array[i];
					array[i] = tmp;
					swapPerformed = true;
				}
			}

		} while (swapPerformed);
	}

	template <typename T, int N>
	void insertionSort(T(&a)[N]) {
		for (int i = 1; i < N; i++) {
			int key = a[i];
			int j = i - 1;
			// to make increasing, use >
			// to make decreasing, use <
			while ((j >= 0) && a[j] < key) {
				a[j + 1] = a[j];
				j--;
			}
			a[j + 1] = key;
		}
	}

	template <typename T, int N>
	void selectionSort(T(&a)[N]) {
		for (int i = 0; i < N - 1; i++) {
			int min = i;
			for (int j = i + 1; j < N; j++) {
				if (a[j] < a[min]) {
					min = j;
				}
			}
			T tmp = a[i];
			a[i] = a[min];
			a[min] = tmp;
		}
	}
};

