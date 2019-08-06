import pandas as pd 
import math
from sklearn.model_selection import train_test_split

k =[2,3,4,5,6,7,8,9,10,11]

datatrain = pd.read_excel('Dataset Tugas 3 AI 1718.xlsx', sheet_name='DataTrain')
x = datatrain.drop(columns=['Berita','Hoax'])
y = datatrain['Hoax'].values

def eucliddistance(train, test):
    row = 4
    distance = 0
    for i in range(row):
        distance += pow(train[0][i] - test[0][i], 2)
    return math.sqrt(distance)

# Xtrain, Xtest, ytrain, ytest = train_test_split(x,y, test_size=0.2, random_state=1, stratify=y)
# print(Xtrain, ytrain)

k_fold = 5
for k1 in k:
    for i in range(k_fold):
        Xtrain, Xtest, ytrain, ytest = train_test_split(x,y, 
                                    test_size=0.2, random=1, stratify=y)
        for j in range(len(Xtest)):
            jarak=[]
            for h in range(len(Xtrain)):
                jarak.append((Xtrain,eucliddistance(Xtrain[h], Xtest[i]))
            jarak.sort(key=iteritem(1))[0:k1]



    

