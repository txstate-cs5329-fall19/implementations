#include "Sorting.h"



Sorting::Sorting()
{
}


Sorting::~Sorting()
{
}

void Sorting::recursiveInsertionSort(int array[], int n) {
	if (n > 1) {
		recursiveInsertionSort(array, n - 1);
		insert(array, n);
	}
}

void Sorting::insert(int array[], int k) {
	int key = array[k];
	int index = k - 1;
	while (index >= 0 && array[index] > key) {
		array[index + 1] = array[index];
		index--;
	}
	array[index + 1] = key;
}