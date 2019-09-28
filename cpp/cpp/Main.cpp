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
#include "Sorting.h"

using namespace std;

template <typename T, int N>
void printArray(T(&a)[N]);

int random(int min, int max);

template<typename T, int N>
void shuffleArray(T(&a)[N]);


int main() {
	cout << "Hello from Boris!" << endl;

	int array[] = { 3, 9, 5, 7, 14, 1, 6, 2, 4, 8, 10, 11, 13, 12 };
	Sorting* s = new Sorting();

	printArray(array);
	cout << "bubble sorted array: ";
	s->bubbleSort(array); // sort with bubble
	printArray(array);
	shuffleArray(array);
	cout << "shuffled array: ";
	printArray(array);
	cout << "insertion sorted array: ";
	s->insertionSort(array); // sort with insertion
	printArray(array);

	return EXIT_SUCCESS;
}

template <typename T, int N>
void printArray(T(&a)[N]) {
	{
		for (int i = 0; i < N; i++) {
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
		srand(time(NULL)); //seeding for the first time only!
		first = false;
	}
	//return min + rand() % (( max - min); //range: [min, max)
	return min + rand() % (max + 1 - min);  //range: [min, max]
}

/**
* Fisher–Yates algorithm
*/
template<typename T, int N>
void shuffleArray(T(&a)[N]) {
	for (int i = 0; i < N; i++) {
		int j = random(0, i);
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
}