class Factorial(object):

	def compute(self, num):
		val = 0
		if num < 1:
			val = 1
		else:
			val = num * self.compute(num-1)
		return val
fact = Factorial()
fact.compute(10)
