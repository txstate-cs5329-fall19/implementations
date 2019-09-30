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

template <typename T, int N>
void shuffleArray(T(&a)[N]);

template <typename T, int N, int K, int M>
void addBinaryInts(T(&a)[N], T(&b)[K], T(&c)[M]);

template <typename T, int N>
int binarySearch(T(&a)[N], int low, int high, int x);

template <typename T, int N>
bool sumSearchBinary(T(&a)[N], int x);

template <typename T, int N>
bool sumSearch(T(&a)[N]);

int main() {
	cout << "Hello from Boris!" << endl;

	static int NUM_ELEMENTS = 13; // N - 1

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

	shuffleArray(array);
	cout << "shuffled array: ";
	printArray(array);

	cout << "recursive insertion sorted array: ";
	s->recursiveInsertionSort(array, 13);
	printArray(array);

	shuffleArray(array);
	cout << "shuffled array: ";
	printArray(array);

	cout << "merge sorted array: ";
	s->mergeSort(array, 0, NUM_ELEMENTS);
	printArray(array);


	int idx = binarySearch(array, 0, 13, 11);
	cout << "search for 11, should be at index 10: " << idx << endl;

	bool sumExists = sumSearchBinary(array, 3);
	cout << "do 2 nums exist that give sum of 3? " << sumExists << endl;

	sumExists = sumSearchBinary(array, 90);
	cout << "do 2 nums exist that give sum of 90? " << sumExists << endl;

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

template <typename T, int N>
bool sumSearchBinary(T(&a)[N], int x) {
	Sorting* s = new Sorting();
	s->mergeSort(a, 0, 13);
	for (int i = 0; i < N; i++) {
		if (binarySearch(a, 0, 13, x - a[i]) != NULL) {
			return true;
		}
	}
	return false;
}

template <typename T, int N>
bool sumSearch(T(&a)[N]) {

}

template <typename T, int N> 
int binarySearch(T(&a)[N], int low, int high, int x) {

	if (low > high) 
		return NULL;

	int mid = (low + high)/2;//low + (high - low) / 2;

	// If the element is present at the middle 
	if (a[mid] == x)
		return mid;

	// If element is smaller than mid, then 
	// it can only be present in left subarray 
	if (a[mid] > x)
		return binarySearch(a, low, mid - 1, x);

	// Else the element can only be present 
	// in right subarray 
	return binarySearch(a, mid + 1, high, x);
}