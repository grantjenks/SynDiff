class SimpleClass(object):

	def __init__(self):
		self.varList = []
		self.tooLongOfAVariableNameSomeWouldSay = 0

	def printList(self):
		for i, v in enumerate(self.varList):
			print "Index: ", i, " Value: ", v

	def sortList(self):
		self.varList.sort()

	def get(self):
		return self.varList
	
	def set(self, variable):
		self.varList.append(variable)
	
	def functionForNothing(self, n):
		while self.tooLongOfAVariableNameSomeWouldSay < n:
			self.tooLongOfAVariableNameSomeWouldSay += 1
			print "Hello World Program?"
		self.tooLongOfAVariableNameSomeWouldSay = 0
sc = SimpleClass()
