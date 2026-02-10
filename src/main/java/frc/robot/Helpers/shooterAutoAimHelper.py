import numpy as np
from scipy.optimize import curve_fit

def fileToArray():
    listOfPoints = []
    file = open("listedPointsOfShooter.txt", "r")
    line = file.readline()
    while line:
        listd = line.strip().split()
        listd = list(listd)
        listOfPoints.append(listd)
        line = file.readline()
     #   print(listd)
    file.close
    return listOfPoints


def thirdDegreePoly(a, b, c, d, e, f):
    return a +" + " + b+"x + " + c+"y + " + d*"x^2 + " + e+"y^2 + " + f"xy"
def func(xy, a, b, c, d, e, f):
    x, y = xy
    return a + b*x + c*y + d*x**2 + e*y**2 + f*x*y

def makeSurface():
    array = fileToArray()
   # array.transpose
    print(array)
    popt , pcov = curve_fit(func, (array[0], array[1]), array[2])
    array.T
    #print(thirdDegreePoly(*pcov))
    return thirdDegreePoly(*pcov)

makeSurface()