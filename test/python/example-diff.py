# Demonstrates private variables and methods

class Critter(object):
    """My virtual pet"""
    def __init__(self, name, mood):
        print "My new critter has been born!"
        self.name = name            # public attribute
        self.__mood = mood          # private attribute

    def talk(self):
        print "\n Hi! I'm", self.name
        print "Right now I feel", self.__mood, "\n"

    def __private_method(self):
        print "This is a private method."

    def public_method(self):
        print "This is a public method."
        self.__private_method()

# main
crit = Critter(name = "Poochie", mood = "happy")
crit.talk()
crit.public_method()
