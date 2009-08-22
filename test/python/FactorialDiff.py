class Factorial(object):

	def compute(self, num):
		value = 0
		unusedVar = 5
		if num < 1:
			value = 1
		else:
			value = num * self.compute(num-1)
		return value
fact = Factorial()
fact.compute(9)
