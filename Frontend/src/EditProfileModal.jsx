import { useEffect, useState } from "react";
import {
  X,
  User,
  MapPin,
  Briefcase,
  GraduationCap,
  Heart,
  Target,
  IndianRupee,
} from "lucide-react";

function calculateCompletion(data){

    if(!data) return 0;

    const fields=[
        "fullName",
        "age",
        "city",
        "state",
        "religion",
        "community",
        "education",
        "profession",
        "annualIncome",
        "about",
        "lifeGoals",
        "partnerExpectations"
    ];

    const completed=fields.filter(f=>data[f] && data[f].toString().trim()!=="").length;

    return Math.round((completed/fields.length)*100);

}

function getAISuggestions(data) {
  const suggestions = [];

  if (!data.about || data.about.length < 50)
    suggestions.push("Write a more detailed About section.");

  if (!data.interests || data.interests.length < 10)
    suggestions.push("Add your hobbies and interests.");

  if (!data.lifeGoals || data.lifeGoals.length < 20)
    suggestions.push("Describe your life goals.");

  if (!data.partnerExpectations || data.partnerExpectations.length < 20)
    suggestions.push("Mention your partner expectations.");

  if (!data.annualIncome)
    suggestions.push("Add your annual income.");

  if (!data.education)
    suggestions.push("Complete your education details.");

  if (suggestions.length === 0) {
    suggestions.push("Excellent profile! You're likely to get better matches.");
  }

  return suggestions;
}

export default function EditProfileModal({
  isOpen,
  profile,
  onClose,
  onSave,
}) {
  const [formData, setFormData] = useState({});

  useEffect(() => {
    if (profile) {
      setFormData(profile);
    }
  }, [profile]);

  if (!isOpen) return null;

  const change = (e) =>
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  const completion = calculateCompletion(formData);
  const aiSuggestions = getAISuggestions(formData);

  return (
    <div className="modal-overlay">

      <div className="edit-profile-modal">

        <button className="close-btn" onClick={onClose}>
          <X size={22}/>
        </button>

        <div className="modal-header">

          <img
            src={profile.photo}
            className="edit-avatar"
            alt=""
          />

          <h2>Edit Profile</h2>

          <p>Keep your profile updated for better AI matches.</p>
          <div className="completion-wrapper">

            <div
                className="completion-circle"
                style={{
                     background:`conic-gradient(
                        #ec4899 ${completion*3.6}deg,
                        rgba(255,255,255,.08) 0deg
                    )`
                }}
            >

        <div className="completion-inner">

            <span>{completion}%</span>

        </div>

    </div>

    <h4>Profile Completion</h4>

</div>

        </div>

        <form
          onSubmit={(e)=>{
            e.preventDefault();
            onSave(formData);
          }}
        >

          <h3>Personal Information</h3>

          <div className="grid-2">

            <Input
              icon={<User size={18}/>}
              label="Full Name"
              name="fullName"
              value={formData.fullName}
              onChange={change}
            />

            <Input
              label="Age"
              name="age"
              type="number"
              value={formData.age}
              onChange={change}
            />

            <Input
              icon={<MapPin size={18}/>}
              label="City"
              name="city"
              value={formData.city}
              onChange={change}
            />

            <Input
              label="State"
              name="state"
              value={formData.state}
              onChange={change}
            />

            <Input
              label="Religion"
              name="religion"
              value={formData.religion}
              onChange={change}
            />

            <Input
              label="Community"
              name="community"
              value={formData.community}
              onChange={change}
            />

          </div>

          <h3>Career</h3>

          <div className="grid-2">

            <Input
              icon={<GraduationCap size={18}/>}
              label="Education"
              name="education"
              value={formData.education}
              onChange={change}
            />

            <Input
              icon={<Briefcase size={18}/>}
              label="Profession"
              name="profession"
              value={formData.profession}
              onChange={change}
            />

            <Input
              icon={<IndianRupee size={18}/>}
              label="Annual Income"
              name="annualIncome"
              value={formData.annualIncome}
              onChange={change}
            />

          </div>

          <h3>About</h3>

          <textarea
            rows="5"
            name="about"
            value={formData.about || ""}
            onChange={change}
          />

          <h3>Life Goals</h3>

          <textarea
            rows="4"
            name="lifeGoals"
            value={formData.lifeGoals || ""}
            onChange={change}
          />

          <h3>Partner Expectations</h3>

          <textarea
            rows="4"
            name="partnerExpectations"
            value={formData.partnerExpectations || ""}
            onChange={change}
          />
          <h3>🤖 AI Suggestions</h3>

        <div className="ai-card">
            <div className="ai-title">
                🤖 SoulSync AI Suggestions
            </div>

            <ul>
                {aiSuggestions.map((tip, index) => (
                    <li key={index}>✨ {tip}</li>
                ))}
            </ul>
        </div>

          <div className="modal-footer">

            <button
              type="button"
              className="cancel-btn"
              onClick={onClose}
            >
              Cancel
            </button>

            <button
              className="save-btn"
            >
              ❤️ Save Changes
            </button>

          </div>

        </form>

      </div>

    </div>
  );
}

function Input({
  icon,
  label,
  name,
  value,
  onChange,
  type="text"
}){

  return(

    <div className="input-group">

      <label>{label}</label>

      <div className="input-box">

        {icon}

        <input
          type={type}
          name={name}
          value={value || ""}
          onChange={onChange}
        />

      </div>

    </div>

  )

}