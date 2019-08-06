import pandas as pd 
from pandas import *
from openpyxl import load_workbook
from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier
import numpy as np 
from sklearn.model_selection import cross_val_score, GridSearchCV

#I used primes to declared the K values
kval =[2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67]

datatrain = pd.read_excel('Dataset Tugas 3 AI 1718.xlsx', sheet_name='DataTrain')
x = datatrain.drop(columns=['Berita','Hoax'])
y = datatrain['Hoax'].values

#This code is hypertunning manually by the K values that have been declared
for k in kval:
    knn = KNeighborsClassifier(n_neighbors=k)
    crossval = cross_val_score(knn, x, y, cv=3)
    print('K=', k, ' crossval mean= {}'.format(np.mean(crossval)))
    
#This code is hypertunning by itself :P, to find the best params
knn2 = KNeighborsClassifier()
param_grid = {'n_neighbors':np.arange(1,100)} #create a dictionary of all k values to test to find the best params
knn_gscv = GridSearchCV(knn2, param_grid, cv=3) #use gridsearch to test all values for n_neigbors
knn_gscv.fit(x,y)
print('best params:',knn_gscv.best_params_,' best_score: ',knn_gscv.best_score_)

#After this experiments, I just realized the different cross validation values 
#also affect the score. even the best K!!! so choose wisely

datatest = datatrain = pd.read_excel('Dataset Tugas 3 AI 1718.xlsx', sheet_name='DataTest')
test = datatest.drop(columns=['Berita','Hoax'])
knntest = KNeighborsClassifier(n_neighbors=59)
knntest.fit(x, y)
result = knntest.predict(test)

#import the result to the excel
hasil = pd.DataFrame({'Hoax':result})
writer=ExcelWriter('Dataset Tugas 3 AI 1718.xlsx')
book = load_workbook('Dataset Tugas 3 AI 1718.xlsx')
writer.book = book 
writer.sheets = dict((ws.title, ws) for ws in book.worksheets)
hasil.to_excel(writer, sheet_name='DataTest',index=False, startcol=6)
writer.save()
