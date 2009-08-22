class SimpleClass(object):

	def __init__(self):
		self.varList = []

	def set(self, variable):
		self.varList.append(variable)
		
	def get(self):
		return self.varList

	def printList(self):
		for i, v in enumerate(self.varList):
			print "Index: ", i, " Value: ", v

	def sortAndPrint(self):
		self.varList.sort()
		for i, v in enumerate(self.varList):
			print "Index: ", i, " Value: ", v 
sc = SimpleClass()
