class BasicQueue:

    def __init__(self, num):
        self.num = num
        self.front = -1
        self.end = -2
        
    def size(self):
        if self.front == -1 and self.end == -1:
            return 0
        else:
            return self.end - self.front

a = BasicQueue(4)
a.size()
