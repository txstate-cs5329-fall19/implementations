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

template<typename T, int N, int K, int M>
void addBinaryInts(T(&a)[N], T(&b)[K], T(&c)[M]);


int main() {
	cout << "Hello from Boris!" << endl;

	int array[] = { 3, 9, 5, 7, 14, 1, 6, 2, 4, 8, 10, 11, 13, 12 };
	Sorting* s = new Sorting();
	cout << "initial array: ";
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

	shuffleArray(array);
	cout << "shuffled array: ";
	printArray(array);

	cout << "selection sorted array: ";
	s->selectionSort(array);
	printArray(array);


	int A[4] = { 0,1,0,1 };
	int B[4] = { 0,1,0,1 };
	int C[5] = { 0 };
	addBinaryInts(A, B, C);

	for (int i = 4; i >= 0; i--) {
		cout << C[i];
	}
	cout << endl;
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
* Durstenfeld implementation of Fisher–Yates shuffle
*/
template<typename T, int N>
void shuffleArray(T(&a)[N]) {
	for (int i = N-1; i >= 1; i--) {
		int j = random(0, i);
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
}

template<typename T, int N, int K, int M>
void addBinaryInts(T(&a)[N], T(&b)[K], T(&c)[M]) {
	int carry = 0;
	for (int i = N; i >= 0; i--) {
		c[i+1] = (a[i] + b[i] + carry) % 2;
		if (a[i] + a[i] + carry >= 2) {
			carry = 1;
		} else {
			carry = 0;
		}
	}
	c[0] = carry;
}