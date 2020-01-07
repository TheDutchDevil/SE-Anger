'''
Script that takes the dataset used for the evaluation
of the paper by Qiu etal. and converts into two
.csv files that can be used to evaluate our approach.

One .csv file is a list of all lines of text, combined
with their unique identifier. The second one is a 
list of ground truth items, where each items is a feature,
with the accompanying sentence id in which the feature occurs.

The combination of these two files can be used to compute 
precision and recall.
'''

import sys
import os
import csv

def write_array_to_csv(data, filename):
    with open(filename, "w", newline="\n") as f:
        writer = csv.writer(f)
        writer.writerows(data)


if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Wrong number of arguments provided")
        exit(1)
    
    path = sys.argv[1]

    if not os.path.isfile(path):
        print(f"There is no file at {path}")
        exit(1)

    output = [["id", "line"]]
    ground_truth = [["id", "feature"]]

    with open(path) as f:
        review_lines = f.readlines()

        i = 1

        for line in review_lines:
            if line != "" and not line.isspace() and line[0] != "*" and not line.startswith("[t]"):
                parts = line.split("##")

                if len(parts) > 2:
                    sent = ""
                    for j in range(1, len(parts)):
                        sent += parts[j]
                else:
                    sent = parts[1]

                features = parts[0].split(",")

                for feature in features:
                    if len(feature) > 0:
                        ground_truth.append([i, feature.split("[")[0].strip()])

                output.append([i, sent.replace("\n", "")])
                i += 1

        write_array_to_csv(output, f"{path}-processed.csv")
        write_array_to_csv(ground_truth, f"{path}-ground_truth.csv")

