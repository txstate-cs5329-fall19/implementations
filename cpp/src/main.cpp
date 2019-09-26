//============================================================================
// Name        : main.cpp
// Author      : Boris
// Version     : 0.1
// Description : Initial main file with sorting algorithms in C++, Ansi-style
//============================================================================

#include <iostream>
#include <stdio.h>      /* printf, NULL */
#include <stdlib.h>     /* srand, rand */
#include <time.h>       /* time */

using namespace std;

template <typename T, int N>
void printArray(T (&a) [N]);

int random(int min, int max);

template<typename T, int N>
void shuffleArray(T (&a)[N]);

template <typename T, int N>
void bubbleSort(T (&array)[N]);

template <typename T, int N>
void insertionSort(T (&a) [N]);


int main() {
	cout << "Hello from Boris!" << endl;

	int array[] = { 3, 9, 5, 7, 14, 1, 6, 2, 4, 8, 10, 11, 13, 12 };

	printArray(array);
	bubbleSort(array); // sort with bubble
	printArray(array);
	shuffleArray(array);
	printArray(array);
	insertionSort(array); // sort with insertion
	printArray(array);

	return 0;
}

template <typename T, int N>
void printArray(T (&a)[N]) {
	 {
	    for (int i = 0; i < N; ++i) {
	    	//a[i] = T(); // reset all elements
	    	cout << a[i] << ", ";
	    }
	    cout << endl;
	}
}

int random(int min, int max) //range : [min, max]
{
   static bool first = true;
   if (first)
   {
      srand( time(NULL) ); //seeding for the first time only!
      first = false;
   }
   //return min + rand() % (( max - min); //range: [min, max)
   return min + rand() % (max + 1 - min);  //range: [min, max]
}

/**
 * Fisher–Yates algorithm
 */
template<typename T, int N>
void shuffleArray(T (&a)[N]) {
	for (int i = 0; i < N; i++) {
		int j = random(0, i);
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
}

template <typename T, int N>
void bubbleSort(T (&array)[N]) {
	bool swapPerformed;
	do {
		swapPerformed = false;

		for (int i = 0; i < N; i++) {
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
void insertionSort(T (&a) [N]) {
	for (int i = 1; i < N; i++) {
		int key = a[i];
		int j = i - 1;

		while ((j >= 0) && a[j] > key) {
			a[j + 1] = a[j];
			j--;
		}
		a[j + 1] = key;
	}
}

